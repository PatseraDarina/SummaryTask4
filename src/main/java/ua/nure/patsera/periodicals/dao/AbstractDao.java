package ua.nure.patsera.periodicals.dao;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.model.BaseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Дарина on 03.09.2017.
 */
public  class AbstractDao<T extends BaseEntity, PK> implements GenericDao<T, PK> {

  // public static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    protected abstract PreparedStatement prepareCreateQuery(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement prepareReadQuery(Connection connection, PK key) throws SQLException;

    protected abstract PreparedStatement prepareUpdateQuery(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement prepareDeleteQuery(Connection connection, PK key) throws SQLException;


    @Override
    public PK create(Connection connection, T objectToCreate) throws SQLException {
        PK generatedId;
        PreparedStatement preparedStatement = prepareCreateQuery(connection, objectToCreate);
        preparedStatement.executeUpdate();
        generatedId = (PK) new Integer(preparedStatement.getGeneratedKeys().getInt(1));
        return generatedId;
    }

    @Override
    public T read(Connection connection, PK id) throws SQLException {
        PreparedStatement preparedStatement = prepareReadQuery(connection, id);

    }

    @Override
    public void update(Connection connection, T objectToUpdate) {

    }

    @Override
    public void delete(Connection connection, T objectToDelete) {

    }
}