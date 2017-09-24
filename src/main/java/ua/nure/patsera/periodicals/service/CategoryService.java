package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.dao.entityDaoImpl.CategoryDaoImpl;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ICategoryDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.dao.utility.ConnectionPool;

import java.util.List;

/**
 * Created by Дарина on 08.09.2017.
 */
public class CategoryService {
    private final TransactionManager transactionManager;

    private final ICategoryDao categoryDao;

    public CategoryService(TransactionManager transactionManager, ICategoryDao categoryDao) {
        this.transactionManager = transactionManager;
        this.categoryDao = categoryDao;
    }

    public void add(Category entity) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>) connection ->{
            categoryDao.create(connection, entity);
            return null;});
    }

    public boolean contains(String name) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                categoryDao.getCategoryByName(connection, name)) != null;
    }

    public List<Category> getAllCategory() {
        try {
            return transactionManager.doTransaction(categoryDao::readAll);
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Category getCategory(String name) {
        try {
            return transactionManager.doTransaction(connection ->
                    categoryDao.getCategoryByName(connection, name));
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
