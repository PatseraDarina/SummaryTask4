package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.Reader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface IReaderDao extends IBaseEntityDao<Reader, Integer> {
    Reader getByLoginData(Connection connection, String email, String password) throws SQLException;

}
