package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.User;
import ua.nure.patsera.periodicals.dto.RegistrationDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface IUserDao extends IBaseEntityDao<User, Integer> {
    User getByLoginData(Connection connection, String email) throws SQLException;
    String getUserRoleByEmail(Connection connection, String email) throws SQLException;
    void setMoney(Connection connection, int id, double money) throws SQLException;
    User getByLoginPswd(Connection connection, String email, String password) throws SQLException;

    List<RegistrationDto> getRegistrationDto(Connection connection) throws SQLException;
}
