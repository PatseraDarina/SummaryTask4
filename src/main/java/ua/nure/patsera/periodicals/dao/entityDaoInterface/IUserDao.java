package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface IUserDao extends IBaseEntityDao<User, Integer> {
    User getByLoginData(Connection connection, String email, String password) throws SQLException;
    String getUserRoleByEmail(Connection connection, String email) throws SQLException;
}
