package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.dao.entityDaoImpl.CategoryDaoImpl;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.dao.utility.ConnectionPool;

/**
 * Created by Дарина on 08.09.2017.
 */
public class CategoryService {
    private final TransactionManager transactionManager;
    private final CategoryDaoImpl categoryDao;

    public CategoryService(TransactionManager transactionManager, CategoryDaoImpl categoryDao) {
        this.transactionManager = transactionManager;
        this.categoryDao = categoryDao;
    }

    /*public void addCategory(Category category) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection ->
                categoryDao.create(connection, category));
    }*/

    public static void main(String[] args) {

    }
}
