package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.District;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IDistrictDao;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;

/**
 * Created by Дарина on 12.09.2017.
 */
public class DistrictService implements IService<District> {
    TransactionManager transactionManager;
    IDistrictDao districtDao;

    public DistrictService(TransactionManager transactionManager, IDistrictDao districtDao) {
        this.transactionManager = transactionManager;
        this.districtDao = districtDao;
    }

    @Override
    public void add(District entity) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection ->
                districtDao.create(connection, entity));
    }

    @Override
    public boolean contains(String name) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                districtDao.getDistrictByName(connection, name)) != null;
    }

    public District getDistrict(String name) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                districtDao.getDistrictByName(connection, name));
    }

}
