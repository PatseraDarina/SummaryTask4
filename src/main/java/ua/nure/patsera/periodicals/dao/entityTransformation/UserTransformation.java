package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class UserTransformation implements ResultSetTransformation<User> {
    @Override
    public User getDBObject(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            user = parseReader(resultSet);
        }
        return user;
    }

    @Override
    public List<User> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parseReader(resultSet));
        }
        return list;
    }

    private User parseReader(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setFirstName(resultSet.getString(2));
        user.setMiddleName(resultSet.getString(3));
        user.setLastName(resultSet.getString(4));
        user.setPhone(resultSet.getString(5));
        user.setFlatNumber(resultSet.getInt(6));
        user.setHouseNumber(resultSet.getString(7));
        user.setPassword(resultSet.getString(8));
        user.setEmail(resultSet.getString(9));
        user.setIdDistrict(resultSet.getInt(10));
        user.setStreet(resultSet.getString(11));
        return user;
    }
}
