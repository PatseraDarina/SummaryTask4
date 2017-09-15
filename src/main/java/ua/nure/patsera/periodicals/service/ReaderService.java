package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.Reader;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IReaderDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.dto.LoginDto;
import ua.nure.patsera.periodicals.dto.RegistrationDto;
import ua.nure.patsera.periodicals.exceptions.AuthorizationException;
import ua.nure.patsera.periodicals.exceptions.Messages;
import ua.nure.patsera.periodicals.exceptions.RegistrationException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.validation.Validator;

import java.util.Optional;

/**
 * Created by Дарина on 11.09.2017.
 */
public class ReaderService implements IService<Reader> {

    private final TransactionManager transactionManager;
    private final IReaderDao readerDao;
    private final DistrictService districtService;


    public ReaderService(TransactionManager transactionManager, IReaderDao readerDao, DistrictService districtService) {
        this.transactionManager = transactionManager;
        this.readerDao = readerDao;
        this.districtService = districtService;
    }

    @Override
    public void add(Reader reader) throws TransactionInterruptedException {
         transactionManager.doTransaction((Operation<Void>) connection -> {
             readerDao.create(connection, reader);
         return null;});
    }


    @Override
    public boolean contains(String name) throws TransactionInterruptedException {
      //  return getByLogin(name) != null;
        return true;
    }

    public Reader getByLogin(String email, String password) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                readerDao.getByLoginData(connection, email, password));
    }

    public Reader login(LoginDto loginDto) throws AuthorizationException, TransactionInterruptedException {
        checkLoginData(loginDto);
            Reader reader = getByLogin(loginDto.getEmail(), loginDto.getPassword());
            if (reader == null) {
                throw new AuthorizationException(Messages.INVALID_LOGIN);
            }
            return reader;
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
                Validator.confirmPassword(registrationDto.getPassword(), registrationDto.getConfirmPassword()) &&
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
                Reader newReader = new Reader();
                newReader.setEmail(registrationDto.getEmail());
                newReader.setPassword(registrationDto.getPassword());
                newReader.setHouseNumber(registrationDto.getHouseNumber());
                newReader.setPhone(registrationDto.getPhone());
                newReader.setFlatNumber(registrationDto.getFlatNumber());
                newReader.setFirstName(registrationDto.getFirstName());
                newReader.setLastName(registrationDto.getLastName());
                newReader.setMiddleName(registrationDto.getMiddleName());
                newReader.setStreet(registrationDto.getStreet());
                newReader.setIdDistrict(districtService.getDistrict(registrationDto.getDistrict()).getId());
                add(newReader);
            }
        } catch (TransactionInterruptedException e) {
            throw new RegistrationException(Messages.TRY_AGAIN);
        }
    }
}
