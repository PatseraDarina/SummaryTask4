package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.City;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ICityDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IReaderDao;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.exceptions.AuthorizationException;
import ua.nure.patsera.periodicals.exceptions.Messages;
import ua.nure.patsera.periodicals.exceptions.RegistrationException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.validation.Validator;

import java.util.Optional;

/**
 * Created by Дарина on 12.09.2017.
 */
public class CityService implements IService<City> {
    private final TransactionManager transactionManager;
    private final ICityDao cityDao;

    public CityService(TransactionManager transactionManager, ICityDao cityDao) {
        this.transactionManager = transactionManager;
        this.cityDao = cityDao;
    }

    public void add(City city) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection ->
                cityDao.create(connection, city));
    }

    @Override
    public boolean contains(String name) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
        cityDao.getCityByName(connection, name)) != null;
    }

    public Optional<City> getByName(String name) throws TransactionInterruptedException {
        return Optional.ofNullable(transactionManager.doTransaction(connection ->
                cityDao.getCityByName(connection, name)));
    }


    private void checkCityData(City city) throws AuthorizationException {
        if (!Validator.isValidEmail(city.getName())) {
            throw new AuthorizationException(Messages.INVALID_ADRESS_DATA);
        }
    }

}
