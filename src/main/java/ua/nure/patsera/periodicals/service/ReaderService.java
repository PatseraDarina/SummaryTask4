package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.Reader;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IReaderDao;
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
public class ReaderService {
    private final TransactionManager transactionManager;
    private final IReaderDao readerDao;


    public ReaderService(TransactionManager transactionManager, IReaderDao readerDao) {
        this.transactionManager = transactionManager;
        this.readerDao = readerDao;
    }

    public void add(Reader reader) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection ->
                readerDao.create(connection, reader));
    }

    public Optional<Reader> getByLogin(String email, String password) throws TransactionInterruptedException {
        return Optional.ofNullable(transactionManager.doTransaction(connection ->
                readerDao.getByLoginData(connection, email, password)));
    }

    public Reader login(LoginDto loginDto) throws AuthorizationException, TransactionInterruptedException {
        checkLoginData(loginDto);
            Optional<Reader> readerOptional = getByLogin(loginDto.getEmail(), loginDto.getPassword());
            if (!readerOptional.isPresent()) {
                throw new AuthorizationException(Messages.NO_READER_WITH_SUCH_LOGIN);
            }
            return readerOptional.get();
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

        Optional<Reader> reader = null;
        try {
            reader = getByLogin(registrationDto.getEmail(), registrationDto.getPassword());
        } catch (TransactionInterruptedException e) {
            throw new RegistrationException(Messages.TRY_AGAIN);
        }
        if (reader.isPresent()) {
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
        }
    }
}
