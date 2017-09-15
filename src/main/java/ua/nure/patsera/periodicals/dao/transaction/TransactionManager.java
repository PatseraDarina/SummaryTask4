package ua.nure.patsera.periodicals.dao.transaction;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.exceptions.Messages;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.dao.utility.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger LOGGER = Logger.getLogger(TransactionManager.class);
    private final ConnectionPool connectionPool;

    public TransactionManager(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public <T> T doTransaction(Operation<T> operation) throws TransactionInterruptedException {
        T result;
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new TransactionInterruptedException(e.getMessage(), e);
        }
        String exceptionMessage;
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
                result = operation.execute(connection);
                connection.commit();
                return result;
            }
            exceptionMessage = Messages.NO_CONNECTION;
        } catch (SQLException e) {
            exceptionMessage = e.getMessage();
            LOGGER.error(e.getMessage());
            exceptionMessage = rollback(connection, exceptionMessage);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new TransactionInterruptedException(exceptionMessage);
    }

    private String rollback(Connection connection, String exceptionMessage) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
            exceptionMessage += System.lineSeparator() + ex.getMessage();
        }
        return exceptionMessage;
    }
}
