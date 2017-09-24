package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.Subscription;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface ISubscriptionDao extends IBaseEntityDao<Subscription, Integer> {
    Subscription getUserSubscription(Connection connection, int idUser, int idPeriodic) throws SQLException;
}
