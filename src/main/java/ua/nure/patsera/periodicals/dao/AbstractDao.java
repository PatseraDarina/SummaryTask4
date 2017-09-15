package ua.nure.patsera.periodicals.dao;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.model.BaseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Дарина on 03.09.2017.
 */
public abstract class AbstractDao<T extends BaseEntity, PK> implements GenericDao<T, PK> {

    protected final ResultSetTransformation<T> resultSetTransformation;

    protected abstract PreparedStatement prepareCreateQuery(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement prepareReadQuery(Connection connection, PK key) throws SQLException;

    protected abstract PreparedStatement prepareUpdateQuery(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement prepareDeleteQuery(Connection connection, PK key) throws SQLException;

    public AbstractDao(ResultSetTransformation<T> resultSetTransformation) {
        this.resultSetTransformation = resultSetTransformation;
    }

    @Override
    public void create(Connection connection, T objectToCreate) throws SQLException {
        PreparedStatement preparedStatement = prepareCreateQuery(connection, objectToCreate);
        preparedStatement.executeUpdate();
    }

    @Override
    public T read(Connection connection, PK id) throws SQLException {
        PreparedStatement preparedStatement = prepareReadQuery(connection, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObject(resultSet);
    }

    @Override
    public void update(Connection connection, T objectToUpdate) throws SQLException {
        PreparedStatement preparedStatement = prepareUpdateQuery(connection, objectToUpdate);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Connection connection, PK id) throws SQLException {
        PreparedStatement preparedStatement = prepareDeleteQuery(connection, id);
        preparedStatement.executeUpdate();

    }
}
