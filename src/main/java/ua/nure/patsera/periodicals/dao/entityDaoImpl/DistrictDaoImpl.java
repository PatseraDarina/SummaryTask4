package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import ua.nure.patsera.periodicals.bean.District;
import ua.nure.patsera.periodicals.dao.AbstractDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IDistrictDao;
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
public class DistrictDaoImpl extends AbstractDao<District, Integer> implements IDistrictDao {
    public DistrictDaoImpl(ResultSetTransformation<District> resultSetTransformation) {
        super(resultSetTransformation);
    }

    @Override
    public District getDistrictByName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = prepareGetDistrictByName(connection, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObject(resultSet);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, District entity) throws SQLException {
        String query = QueryStorage.CREATE_DISTRICT;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setInt(2, entity.getIdCity());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.READ_DISTRICT_BY_ID;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    public List<District> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAllQuery(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObjectsList(resultSet);
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, District entity) throws SQLException {
        String query = QueryStorage.UPDATE_DISTRICT;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.DELETE_DISTRICT;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryStorage.READ_ALL_CITIES;
        return connection.prepareStatement(query);
    }

    private PreparedStatement prepareGetDistrictByName(Connection connection, String name) throws SQLException {
        String query = QueryStorage.READ_DISTRICT_BY_NAME;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        return preparedStatement;
    }
}
