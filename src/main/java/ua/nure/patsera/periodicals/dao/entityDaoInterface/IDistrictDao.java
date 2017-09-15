package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.District;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface IDistrictDao extends IBaseEntityDao<District, Integer> {
    District getDistrictByName(Connection connection, String name) throws SQLException;

}
