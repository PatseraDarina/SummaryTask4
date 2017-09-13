package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import ua.nure.patsera.periodicals.bean.City;
import ua.nure.patsera.periodicals.dao.AbstractDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ICityDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.dao.utility.QueryStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 12.09.2017.
 */
public class CityDaoImpl extends AbstractDao<City, Integer> implements ICityDao {
    public CityDaoImpl(ResultSetTransformation<City> resultSetTransformation) {
       super(resultSetTransformation);
    }

    @Override
    public City getCityByName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = prepareGetCityByName(connection, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObject(resultSet);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, City entity) throws SQLException {
        String query = QueryStorage.CREATE_CITY;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.READ_CITY_BY_ID;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    public List<City> readAll(Connection connection) throws SQLException {
       PreparedStatement preparedStatement = prepareReadAllQuery(connection);
       ResultSet resultSet = preparedStatement.executeQuery();
       return resultSetTransformation.getDBObjectsList(resultSet);
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, City entity) throws SQLException {
        String query = QueryStorage.UPDATE_CITY;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.DELETE_CITY;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryStorage.READ_ALL_CITIES;
        return connection.prepareStatement(query);
    }

    private PreparedStatement prepareGetCityByName(Connection connection, String name) throws SQLException {
        String query = QueryStorage.READ_CITY_BY_NAME;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, name);
        return preparedStatement;
    }
}
