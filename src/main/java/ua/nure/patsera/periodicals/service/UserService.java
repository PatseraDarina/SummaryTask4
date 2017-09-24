package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IUserDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.dto.LoginDto;
import ua.nure.patsera.periodicals.dto.RegistrationDto;
import ua.nure.patsera.periodicals.exceptions.AuthorizationException;
import ua.nure.patsera.periodicals.exceptions.Messages;
import ua.nure.patsera.periodicals.exceptions.RegistrationException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.validation.Validator;

import java.util.List;

/**
 * Created by Дарина on 11.09.2017.
 */
public class UserService {

    private final TransactionManager transactionManager;
    private final IUserDao userDao;
    private final DistrictService districtService;


    public UserService(TransactionManager transactionManager, IUserDao userDao, DistrictService districtService) {
        this.transactionManager = transactionManager;
        this.userDao = userDao;
        this.districtService = districtService;
    }

    public void add(User user) throws TransactionInterruptedException {
         transactionManager.doTransaction((Operation<Void>) connection -> {
             userDao.create(connection, user);
         return null;});
    }

    public boolean contains(String name) throws TransactionInterruptedException {
        return getByLogin(name) != null;
    }

    public User getByLogin(String email) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                userDao.getByLoginData(connection, email));
    }

    public User getByLoginPswd(String email, String password) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                userDao.getByLoginPswd(connection, email, password));
    }

    public User getUser(int id) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
        userDao.read(connection, id));
    }

    public User login(LoginDto loginDto) throws AuthorizationException, TransactionInterruptedException {
        checkLoginData(loginDto);
            User user = getByLoginPswd(loginDto.getEmail(), loginDto.getPassword());
            if (user == null) {
                throw new AuthorizationException(Messages.INVALID_LOGIN);
            }
            return user;
    }

    public void register(RegistrationDto registrationDto) throws RegistrationException {
        checkRegisterData(registrationDto);
        registerReader(registrationDto);
    }

    private void checkLoginData(LoginDto loginDto) throws AuthorizationException {
        if (!Validator.isValidEmail(loginDto.getEmail())) {
            throw new AuthorizationException(Messages.INVALID_LOGIN);
        }
    }

    private void checkRegisterData(RegistrationDto registrationDto) throws RegistrationException {
        if ( !Validator.isValidEmail(registrationDto.getEmail()) &&
                Validator.isValidPassword(registrationDto.getPassword()) &&
                Validator.isValidName(registrationDto.getFirstName()) &&
                Validator.isValidName(registrationDto.getLastName()) &&
                Validator.isValidName(registrationDto.getMiddleName())) {
            throw new RegistrationException(Messages.INVALID_REGISTRATION_DATA);
        }
    }

    private void registerReader(RegistrationDto registrationDto) throws RegistrationException {
        try {
            if (contains(registrationDto.getEmail())) {
                throw new RegistrationException(Messages.READER_EXISTS);
            } else {
                User newUser = new User();
                newUser.setEmail(registrationDto.getEmail());
                newUser.setPassword(registrationDto.getPassword());
                newUser.setHouseNumber(registrationDto.getHouseNumber());
                newUser.setPhone(registrationDto.getPhone());
                newUser.setFlatNumber(registrationDto.getFlatNumber());
                newUser.setFirstName(registrationDto.getFirstName());
                newUser.setLastName(registrationDto.getLastName());
                newUser.setMiddleName(registrationDto.getMiddleName());
                newUser.setStreet(registrationDto.getStreet());
                newUser.setIdDistrict(districtService.getDistrict(registrationDto.getDistrict()).getId());
                add(newUser);
            }
        } catch (TransactionInterruptedException e) {
            throw new RegistrationException(Messages.REGISTRATION_NOT_SUCCESS);
        }
    }
    
    public String getUserRole(String email) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection -> userDao.getUserRoleByEmail(connection, email));
    }

    public void updateUser(User user) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>) connection -> {
            userDao.update(connection, user);
            return null;});
    }

    public void setAccount(int id, double money) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection ->{
        userDao.setMoney(connection, id, money);
        return null;});
    }

    public List<RegistrationDto> getAllUserDto() throws TransactionInterruptedException {
        return transactionManager.doTransaction(userDao::getRegistrationDto);
    }
}
