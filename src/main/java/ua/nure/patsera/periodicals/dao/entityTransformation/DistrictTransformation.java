package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.District;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class DistrictTransformation implements ResultSetTransformation<District> {
    @Override
    public District getDBObject(ResultSet resultSet) throws SQLException {
        District district = new District();
        while (resultSet.next()) {
            parseDistrict(resultSet);
        }
        return district;
    }

    @Override
    public List<District> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<District> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parseDistrict(resultSet));
        }
        return list;
    }

    private District parseDistrict(ResultSet resultSet) throws SQLException {
        District district = new District();
        district.setId(resultSet.getInt(1));
        district.setName(resultSet.getString(2));
        return district;
    }
}
