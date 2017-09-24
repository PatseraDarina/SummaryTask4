package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.bean.Subscription;
import ua.nure.patsera.periodicals.dao.AbstractDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ISubscriptionDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.dao.utility.QueryStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 16.09.2017.
 */
public class SubscriptionDaoImpl extends AbstractDao<Subscription, Integer> implements ISubscriptionDao {
    public SubscriptionDaoImpl(ResultSetTransformation<Subscription> resultSetTransformation) {
        super(resultSetTransformation);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, Subscription entity) throws SQLException {
        String query = QueryStorage.CREATE_SUBSCRIPTION;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, entity.getIdPeriodical());
        preparedStatement.setInt(2, entity.getIdReader());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.READ_SUBSCRIPTION_BY_ID;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    public List<Subscription> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAllQuery(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObjectsList(resultSet);
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, Subscription entity) throws SQLException {
        String query = QueryStorage.UPDATE_SUBSCRIPTION;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, entity.getIdPeriodical());
        preparedStatement.setInt(2, entity.getIdReader());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.DELETE_SUBSCRIPTION;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryStorage.READ_ALL_SUBSCRIPTION;
        return connection.prepareStatement(query);
    }

    @Override
    public Subscription getUserSubscription(Connection connection, int idUser, int idPeriodic) throws SQLException {
        PreparedStatement preparedStatement = prepareReadSubscriptQuery(connection, idUser, idPeriodic);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObject(resultSet);
    }

    private PreparedStatement prepareReadSubscriptQuery(Connection connection, int idUser, int idPeriodic) throws SQLException {
        String query = QueryStorage.READ_USER_SUBSCRIPTION;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idPeriodic);
        preparedStatement.setInt(2, idUser);
        return preparedStatement;
    }
}
