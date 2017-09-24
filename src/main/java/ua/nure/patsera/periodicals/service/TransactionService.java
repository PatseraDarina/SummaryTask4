package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.bean.Subscription;
import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ISubscriptionDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ITransactionDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IUserDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.dto.LoginDto;
import ua.nure.patsera.periodicals.dto.RegistrationDto;
import ua.nure.patsera.periodicals.exceptions.*;
import ua.nure.patsera.periodicals.validation.Validator;

/**
 * Created by Дарина on 23.09.2017.
 */
public class TransactionService {
    private final TransactionManager transactionManager;
    private final ITransactionDao transactionDao;


    public TransactionService(TransactionManager transactionManager, ITransactionDao transactionDao) {
        this.transactionManager = transactionManager;
        this.transactionDao = transactionDao;
    }

    public void addSubscription(Subscription subscription, User user) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            transactionDao.subscribe(connection, subscription, user);
            return null;
        });
    }

    public void deleteSubscription(int idSubscription, User user) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            transactionDao.unsubscribe(connection, idSubscription, user);
            return null;
        });
    }
}
