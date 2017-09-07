package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.Periodicity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class PeriodicityTransformation implements ResultSetTransformation<Periodicity> {
    @Override
    public Periodicity getDBObject(ResultSet resultSet) throws SQLException {
        Periodicity periodicity = new Periodicity();
        while (resultSet.next()) {
            parsePeriodicity(resultSet);
        }
        return periodicity;
    }

    @Override
    public List<Periodicity> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<Periodicity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parsePeriodicity(resultSet));
        }
        return list;
    }

    private Periodicity parsePeriodicity(ResultSet resultSet) throws SQLException {
        Periodicity periodicity = new Periodicity();
        periodicity.setId(resultSet.getInt(1));
        periodicity.setIdName(resultSet.getInt(2));
        periodicity.setIdType(resultSet.getInt(3));
        periodicity.setMinMonth(resultSet.getInt(4));
        periodicity.setMaxMonth(resultSet.getInt(5));
        periodicity.setPrice(resultSet.getInt(6));
        return periodicity;
    }
}
