package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.City;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface ICityDao extends IBaseEntityDao<City, Integer> {
    City getCityByName (Connection connection, String name) throws SQLException;
}
