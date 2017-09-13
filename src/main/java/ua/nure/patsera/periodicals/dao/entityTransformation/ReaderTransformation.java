package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class ReaderTransformation implements ResultSetTransformation<Reader> {
    @Override
    public Reader getDBObject(ResultSet resultSet) throws SQLException {
        Reader reader = new Reader();
        while (resultSet.next()) {
            parseReader(resultSet);
        }
        return reader;
    }

    @Override
    public List<Reader> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<Reader> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parseReader(resultSet));
        }
        return list;
    }

    private Reader parseReader(ResultSet resultSet) throws SQLException {
        Reader reader = new Reader();
        reader.setId(resultSet.getInt(1));
        reader.setFirstName(resultSet.getString(2));
        reader.setMiddleName(resultSet.getString(3));
        reader.setLastName(resultSet.getString(4));
        reader.setPhone(resultSet.getString(5));
        reader.setFlatNumber(resultSet.getInt(6));
        reader.setHouseNumber(resultSet.getString(7));
        reader.setPassword(resultSet.getString(8));
        reader.setEmail(resultSet.getString(9));
        reader.setIdDistrict(resultSet.getInt(10));
        reader.setStreet(resultSet.getString(11));
        return reader;
    }
}
