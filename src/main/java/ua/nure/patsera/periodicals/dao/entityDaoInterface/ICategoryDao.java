package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.dao.GenericDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface ICategoryDao extends IBaseEntityDao<Category, Integer> {
    Category getCategoryByName(Connection connection, String name) throws SQLException;
}
