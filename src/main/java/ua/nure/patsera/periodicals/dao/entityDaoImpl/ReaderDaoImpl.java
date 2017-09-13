package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import ua.nure.patsera.periodicals.bean.Reader;
import ua.nure.patsera.periodicals.dao.AbstractDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IReaderDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.dao.utility.QueryStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 08.09.2017.
 */
public class ReaderDaoImpl extends AbstractDao<Reader, Integer> implements IReaderDao {
    public ReaderDaoImpl(ResultSetTransformation<Reader> resultSetTransformation) {
        super(resultSetTransformation);
    }

    @Override
    public List<Reader> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAllQuery(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObjectsList(resultSet);
    }

    @Override
    public Reader getByLoginData(Connection connection, String email, String password) throws SQLException {
        PreparedStatement preparedStatement = prepareReadByLoginQuery(connection, email, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObject(resultSet);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, Reader entity) throws SQLException {
        String query = QueryStorage.CREATE_READER;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getMiddleName());
        preparedStatement.setString(3, entity.getLastName());
        preparedStatement.setString(4, entity.getPhone());
        preparedStatement.setInt(5, entity.getFlatNumber());
        preparedStatement.setString(6, entity.getHouseNumber());
        preparedStatement.setString(7, entity.getPassword());
        preparedStatement.setString(8, entity.getEmail());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.READ_READER_BY_ID;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, Reader entity) throws SQLException {
        String query = QueryStorage.UPDATE_READER;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getMiddleName());
        preparedStatement.setString(3, entity.getLastName());
        preparedStatement.setString(4, entity.getPhone());
        preparedStatement.setInt(5, entity.getFlatNumber());
        preparedStatement.setString(6, entity.getHouseNumber());
        preparedStatement.setString(7, entity.getPassword());
        preparedStatement.setString(8, entity.getEmail());
        preparedStatement.setInt(9, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.DELETE_READER;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

   private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryStorage.READ_ALL_READERS;
        return connection.prepareStatement(query);
   }

   private PreparedStatement prepareReadByLoginQuery(Connection connection, String email, String password) throws SQLException {
        String query = QueryStorage.READ_BY_LOGIN_DATA_READER;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(9, email);
        preparedStatement.setString(8, password);
        return preparedStatement;
   }
}
