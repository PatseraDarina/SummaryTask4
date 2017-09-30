package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import ua.nure.patsera.periodicals.bean.Subscription;
import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ISubscriptionDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ITransactionDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IUserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDaoImpl implements ITransactionDao {
    private IUserDao userDao;
    private ISubscriptionDao subscriptionDao;

    public TransactionDaoImpl(IUserDao userDao, ISubscriptionDao subscriptionDao) {
        this.userDao = userDao;
        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public void subscribe(Connection connection, Subscription subscription, User user) throws SQLException {
        userDao.update(connection, user);
        subscriptionDao.create(connection, subscription);
    }

    @Override
    public void unsubscribe(Connection connection, int id, User user) throws SQLException {
        userDao.update(connection, user);
        subscriptionDao.delete(connection, id);
    }
}
