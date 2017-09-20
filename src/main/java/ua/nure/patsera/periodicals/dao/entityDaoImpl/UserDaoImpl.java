package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dao.AbstractDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IUserDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.dao.utility.QueryStorage;

import java.sql.*;
import java.util.List;

/**
 * Created by Дарина on 08.09.2017.
 */
public class UserDaoImpl extends AbstractDao<User, Integer> implements IUserDao {

    public UserDaoImpl(ResultSetTransformation<User> resultSetTransformation) {
        super(resultSetTransformation);
    }

    @Override
    public List<User> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAllQuery(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObjectsList(resultSet);
    }

    @Override
    public String getUserRoleByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = prepareReadUserRole(connection, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return getRoleFromRS(resultSet);
    }

    private PreparedStatement prepareReadUserRole(Connection connection, String email) throws SQLException {
        String query = QueryStorage.READ_USER_ROLE;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        return preparedStatement;
    }

    @Override
    public User getByLoginData(Connection connection, String email, String password) throws SQLException {
        PreparedStatement preparedStatement = prepareReadByLoginQuery(connection, email, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObject(resultSet);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, User entity) throws SQLException {
        String query = QueryStorage.CREATE_USER;
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getMiddleName());
        preparedStatement.setString(3, entity.getLastName());
        preparedStatement.setString(4, entity.getPhone());
        preparedStatement.setInt(5, entity.getFlatNumber());
        preparedStatement.setString(6, entity.getHouseNumber());
        preparedStatement.setString(7, entity.getPassword());
        preparedStatement.setString(8, entity.getEmail());
        preparedStatement.setInt(9, entity.getIdDistrict());
        preparedStatement.setString(10, entity.getStreet());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.READ_USER_BY_ID;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, User entity) throws SQLException {
        String query = QueryStorage.UPDATE_USER;
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
        String query = QueryStorage.DELETE_USER;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

   private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryStorage.READ_ALL_USERS;
        return connection.prepareStatement(query);
   }

   private PreparedStatement prepareReadByLoginQuery(Connection connection, String email, String password) throws SQLException {
        String query = QueryStorage.READ_BY_LOGIN_DATA_USER;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement;
   }

   private String getRoleFromRS(ResultSet resultSet) throws SQLException {
        return (resultSet.next()) ? resultSet.getString(1) : null ;
   }
}
