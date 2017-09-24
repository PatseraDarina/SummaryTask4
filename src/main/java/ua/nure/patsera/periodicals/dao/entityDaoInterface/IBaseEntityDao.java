package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.dao.GenericDao;
import ua.nure.patsera.periodicals.dao.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface IBaseEntityDao<T extends BaseEntity, PK> extends GenericDao<T, PK> {
    /**
     * Gets all entries from database from special table
     * which determines by object of type {@code T} that
     * extends {@code BaseEntity}.
     *
     * @param connection lets to set connection with database
     *
     * @return list of objects each of them represents entry from table
     * @throws SQLException
     */
    List<T> readAll(Connection connection) throws SQLException;
}
