package ru.innopolis.tourstore.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

/**
 * Object manages db connections and provides statements
 */
public class DatabaseConnector implements IDatabaseConnector {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConnector.class);

    private Connection connection;
    private Statement statement;

    public DatabaseConnector() {
        try {
            connection = ConnectionPoolFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(SQLConstants.CREATE_TABLE_USERS_QUERY);
            statement.execute(SQLConstants.CREATE_TABLE_TOURS_QUERY);
            statement.execute(SQLConstants.CREATE_TABLE_ORDERS_QUERY);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method returns statement
     */
    public Statement getStatement(){
        return statement;
    }

    /**
     * Method constructs prepared statements
     * @param sql an SQL statement that may contain one or more '?' IN
     * parameter placeholders
     * @return a new default PreparedStatement object containing the
     * pre-compiled SQL statement
     */
    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return preparedStatement;
    }

    /**
     * Closes the specified prepared statement
     * @param ps prepared statement to be closed
     */
    public void closePreparedStatement(PreparedStatement ps){
        try {
            ps.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
