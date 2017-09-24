package ua.nure.patsera.periodicals.service;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Subscription;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ISubscriptionDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.exceptions.Messages;
import ua.nure.patsera.periodicals.exceptions.SubscriptionException;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;

import java.util.List;

/**
 * Created by Дарина on 22.09.2017.
 */
public class SubscriptionService {
    private final TransactionManager transactionManager;
    private final static Logger LOGGER = Logger.getLogger(SubscriptionService.class);

    private final ISubscriptionDao subscriptionDao;

    public SubscriptionService(TransactionManager transactionManager, ISubscriptionDao subscriptionDao) {
        this.transactionManager = transactionManager;
        this.subscriptionDao = subscriptionDao;
    }

    private void add(Subscription entity) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>) connection ->{
            subscriptionDao.create(connection, entity);
            return null;});
    }

    public void delete(int id) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            subscriptionDao.delete(connection, id);
            return null;
        });
    }

    public Subscription getSubscription(int userId, int periodicId) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
        subscriptionDao.getUserSubscription(connection, userId, periodicId));
    }

    public boolean contains(int idUser, int idPeriodic) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                subscriptionDao.getUserSubscription(connection, idUser, idPeriodic)) != null;
    }

    public void addSubscription(Subscription entity) throws TransactionInterruptedException, SubscriptionException {
        if (!contains(entity.getIdReader(), entity.getIdPeriodical())) {
            add(entity);
        } else {
            throw new SubscriptionException(Messages.SUBSCRIBE_EXIST);
        }
    }

    public List<Subscription> getAllSubscription() {
        try {
            return transactionManager.doTransaction(subscriptionDao::readAll);
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
