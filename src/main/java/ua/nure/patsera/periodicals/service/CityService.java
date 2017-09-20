package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.City;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ICityDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;

import java.util.List;
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
         transactionManager.doTransaction((Operation<Void>) connection -> {
             cityDao.create(connection, city);
             return null;
         });
    }

    @Override
    public void update(City entity) throws TransactionInterruptedException {

    }

    @Override
    public void delete(int id) throws TransactionInterruptedException {

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

    public List<City> getAllCity() {
        try {
            return transactionManager.doTransaction(cityDao::readAll);
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
