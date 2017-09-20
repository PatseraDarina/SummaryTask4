package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.Periodical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class PeriodicalTransformation implements ResultSetTransformation<Periodical> {
    @Override
    public Periodical getDBObject(ResultSet resultSet) throws SQLException {
        Periodical periodical = null;
        while (resultSet.next()) {
            periodical = parsePeriodicalsName(resultSet);
        }
        return periodical;
    }

    @Override
    public List<Periodical> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<Periodical> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parsePeriodicalsName(resultSet));
        }
        return list;
    }

    private Periodical parsePeriodicalsName(ResultSet resultSet) throws SQLException {
        Periodical periodical = new Periodical();
        periodical.setId(resultSet.getInt(1));
        periodical.setName(resultSet.getString(2));
        periodical.setIdCategory(resultSet.getInt(3));
        periodical.setPrice(resultSet.getDouble(4));
        periodical.setPhoto(resultSet.getString(5));
        return periodical;
    }
}
