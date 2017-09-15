package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.model.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class CategoryTransformation implements ResultSetTransformation<Category> {
    @Override
    public Category getDBObject(ResultSet resultSet) throws SQLException {
       Category category = null;
       while (resultSet.next()) {
           category = parseCategory(resultSet);
       }
       return category;
    }

    @Override
    public List<Category> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<Category> list = new ArrayList<>();
        while (resultSet.next()) {
           list.add(parseCategory(resultSet));
        }
        return list;
    }

    private Category parseCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(1));
        category.setName(resultSet.getString(2));
        return category;
    }
}
