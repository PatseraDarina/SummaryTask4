package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class SubscriptionTransformation implements ResultSetTransformation<Subscription> {
    @Override
    public Subscription getDBObject(ResultSet resultSet) throws SQLException {
        Subscription subscription = null;
        while (resultSet.next()) {
            subscription = parseSubscription(resultSet);
        }
        return subscription;
    }

    @Override
    public List<Subscription> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<Subscription> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parseSubscription(resultSet));
        }
        return list;
    }

    private Subscription parseSubscription(ResultSet resultSet) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setIdPeriodical(resultSet.getInt(1));
        subscription.setIdReader(resultSet.getInt(2));
        return subscription;
    }
}
