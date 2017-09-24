package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.dao.AbstractDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IPeriodicalsDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.dao.utility.QueryStorage;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 16.09.2017.
 */
public class PeriodicalDaoImpl extends AbstractDao<Periodical, Integer> implements IPeriodicalsDao {
    public PeriodicalDaoImpl(ResultSetTransformation<Periodical> resultSetTransformation) {
        super(resultSetTransformation);
    }

    @Override
    public Periodical getPeriodicalByName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = prepareGetPeriodicalsByName(connection, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObject(resultSet);
    }

    public List<PeriodicalDto> getPeriodicalDto(Connection connection) throws SQLException {
        List<PeriodicalDto> list = new ArrayList<>();
        PreparedStatement preparedStatement = prepareReadPeriodicalDtoQuery(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(parsePeriodicalsDto(resultSet));
        }
        return list;
    }

    public List<PeriodicalDto> getPeriodicalByCategory(Connection connection, String category) throws SQLException {
        List<PeriodicalDto> list = new ArrayList<>();
        PreparedStatement preparedStatement = prepareReadPeriodByCategoryQuery(connection, category);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(parsePeriodicalsDto(resultSet));
        }
        return list;
    }

    private PreparedStatement prepareReadPeriodByCategoryQuery(Connection connection, String category) throws SQLException {
        String query = QueryStorage.READ_PERIODICALS_BY_CATEGORY;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, category);
        return preparedStatement;
    }

    private PeriodicalDto parsePeriodicalsDto(ResultSet resultSet) throws SQLException {
        PeriodicalDto periodical = new PeriodicalDto();
        periodical.setCategory(resultSet.getString(1));
        periodical.setName(resultSet.getString(2));
        periodical.setPrice(resultSet.getDouble(3));
        periodical.setPhoto(resultSet.getString(4));
        periodical.setId(resultSet.getInt(5));
        return periodical;
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, Periodical entity) throws SQLException {
        String query = QueryStorage.CREATE_PERIODICALS;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getIdCategory());
        preparedStatement.setDouble(3, entity.getPrice());
        preparedStatement.setString(4, entity.getPhoto());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.READ_PERIODICALS_BY_ID;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    public List<Periodical> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAllQuery(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObjectsList(resultSet);
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, Periodical entity) throws SQLException {
        String query = QueryStorage.UPDATE_PERIODICALS;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getIdCategory());
        preparedStatement.setDouble(3, entity.getPrice());
        preparedStatement.setString(4, entity.getPhoto());
        preparedStatement.setInt(5, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.DELETE_PERIODICALS;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryStorage.READ_ALL_PERIODICALS;
        return connection.prepareStatement(query);
    }

    private PreparedStatement prepareReadPeriodicalDtoQuery(Connection connection) throws SQLException {
        String query = QueryStorage.READ_ALL_PERIODICALS_DTO;
        return connection.prepareStatement(query);
    }

    private PreparedStatement prepareGetPeriodicalsByName(Connection connection, String name) throws SQLException {
        String query = QueryStorage.READ_PERIODICALS_BY_NAME;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        return preparedStatement;
    }

    @Override
    public List<PeriodicalDto> getPeriodicalByReaderId(Connection connection, int id) throws SQLException {
        List<PeriodicalDto> list = new ArrayList<>();
        PreparedStatement preparedStatement = prepareGetPeriodicalByReader(connection, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(parsePeriodicalsDto(resultSet));
        }
        return list;
    }

    private PreparedStatement prepareGetPeriodicalByReader(Connection connection, int id) throws SQLException {
        String query = QueryStorage.READ_ALL_PERIODICALS_BY_READER;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
