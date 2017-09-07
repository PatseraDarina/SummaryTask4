package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class CityTransformation implements ResultSetTransformation<City> {
    @Override
    public City getDBObject(ResultSet resultSet) throws SQLException {
        City city = null;
        if (resultSet.next()) {
            city = parseCity(resultSet);
        }
        return city;
    }

    @Override
    public List<City> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<City> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parseCity(resultSet));
        }
        return list;
    }

    private  City parseCity(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt(1));
        city.setName(resultSet.getString(2));
        return city;
    }
}
