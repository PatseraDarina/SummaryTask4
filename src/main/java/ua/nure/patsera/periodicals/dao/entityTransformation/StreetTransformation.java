package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.Street;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class StreetTransformation implements ResultSetTransformation<Street> {
    @Override
    public Street getDBObject(ResultSet resultSet) throws SQLException {
        Street street = new Street();
        while (resultSet.next()) {
            parseStreet(resultSet);
        }
        return street;
    }

    @Override
    public List<Street> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<Street> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parseStreet(resultSet));
        }
        return list;
    }

    private Street parseStreet(ResultSet resultSet) throws SQLException {
        Street street = new Street();
        street.setId(resultSet.getInt(1));
        street.setName(resultSet.getString(2));
        street.setIdDistrict(resultSet.getInt(3));
        return street;
    }
}
