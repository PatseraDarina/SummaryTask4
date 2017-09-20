package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.District;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IDistrictDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;

import java.util.List;

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
         transactionManager.doTransaction((Operation<Void>) connection ->{
            districtDao.create(connection, entity);
        return null;});
    }

    @Override
    public void update(District entity) throws TransactionInterruptedException {

    }

    @Override
    public void delete(int id) throws TransactionInterruptedException {

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

    public List<District> getAllDistrict() {
        try {
            return transactionManager.doTransaction(districtDao::readAll);
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
