package ua.nure.patsera.periodicals.dao.entityDaoImpl;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.dao.AbstractDao;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.ICategoryDao;
import ua.nure.patsera.periodicals.dao.entityTransformation.ResultSetTransformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class CategoryDaoImpl extends AbstractDao<Category, Integer> implements ICategoryDao {
    private static final Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class);

    Category category = new Category();

    public CategoryDaoImpl(ResultSetTransformation<Category> resultSetTransformation) {
        super(resultSetTransformation);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, Category entity) throws SQLException {
        String query = category.getCreateSql();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = category.getReadSql();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, Category entity) throws SQLException {
        String query = category.getUpdateSql();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(2, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        String query = category.getDeleteSql();
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
        String query = category.getReadAllSql();
        return connection.prepareCall(query);
    }

}
