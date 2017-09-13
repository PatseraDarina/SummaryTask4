package ua.nure.patsera.periodicals.dao.transaction;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface Operation<T> {
    T execute(Connection connection) throws SQLException;
}
