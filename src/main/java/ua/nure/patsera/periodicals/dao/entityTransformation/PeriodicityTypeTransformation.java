package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.PeriodicityType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class PeriodicityTypeTransformation implements ResultSetTransformation<PeriodicityType> {
    @Override
    public PeriodicityType getDBObject(ResultSet resultSet) throws SQLException {
        PeriodicityType periodicityType = new PeriodicityType();
        while (resultSet.next()) {
            parsePeriodicityType(resultSet);
        }
        return periodicityType;
    }

    @Override
    public List<PeriodicityType> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<PeriodicityType> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parsePeriodicityType(resultSet));
        }
        return list;
    }

    private PeriodicityType parsePeriodicityType(ResultSet resultSet) throws SQLException {
        PeriodicityType periodicityType = new PeriodicityType();
        periodicityType.setId(resultSet.getInt(1));
        periodicityType.setName(resultSet.getString(2));
        return periodicityType;
    }
}
