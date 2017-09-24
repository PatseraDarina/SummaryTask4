package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.dao.AbstractDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ICategoryDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;
import ua.nure.patsera.periodicals.dao.utility.QueryStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class CategoryDaoImpl extends AbstractDao<Category, Integer> implements ICategoryDao {

    public CategoryDaoImpl(ResultSetTransformation<Category> resultSetTransformation) {
        super(resultSetTransformation);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, Category entity) throws SQLException {
        String query = QueryStorage.CREATE_CATEGORY;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.READ_CATEGORY_BY_ID;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, Category entity) throws SQLException {
        String query = QueryStorage.UPDATE_CATEGORY;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.DELETE_CATEGORY;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    public List<Category> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAllQuery(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObjectsList(resultSet);
    }

    private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryStorage.READ_ALL_CATEGORIES;
        return connection.prepareCall(query);
    }

    @Override
    public Category getCategoryByName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = prepareGetCategoryByName(connection, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetTransformation.getDBObject(resultSet);
    }

    private PreparedStatement prepareGetCategoryByName(Connection connection, String name) throws SQLException {
        String query = QueryStorage.READ_CATEGORY_BY_NAME;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        return preparedStatement;
    }
}
