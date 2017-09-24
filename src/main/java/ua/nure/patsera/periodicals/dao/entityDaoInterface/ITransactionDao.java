package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.Subscription;
import ua.nure.patsera.periodicals.bean.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Дарина on 23.09.2017.
 */
public interface ITransactionDao {
    void subscribe(Connection connection, Subscription subscription, User user) throws SQLException;
    void unsubscribe(Connection connection, int id, User user) throws SQLException;
}
