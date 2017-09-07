package ua.nure.patsera.periodicals.utility;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Дарина on 04.09.2017.
 */
public class ConnectionPool {
    DataSource dataSource;

    private ConnectionPool(DataSource dataSource) { this.dataSource = dataSource; }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}