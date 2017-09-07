package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.PeriodicalsName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class PeriodicalsNameTransformation implements ResultSetTransformation<PeriodicalsName> {
    @Override
    public PeriodicalsName getDBObject(ResultSet resultSet) throws SQLException {
        PeriodicalsName periodicalsName = new PeriodicalsName();
        while (resultSet.next()) {
            parsePeriodicalsName(resultSet);
        }
        return periodicalsName;
    }

    @Override
    public List<PeriodicalsName> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<PeriodicalsName> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parsePeriodicalsName(resultSet));
        }
        return list;
    }

    private PeriodicalsName parsePeriodicalsName(ResultSet resultSet) throws SQLException {
        PeriodicalsName periodicalsName = new PeriodicalsName();
        periodicalsName.setId(resultSet.getInt(1));
        periodicalsName.setName(resultSet.getString(2));
        periodicalsName.setIdTopic(resultSet.getInt(3));
        periodicalsName.setIdCategory(resultSet.getInt(4));
        return periodicalsName;
    }
}
