package ru.innopolis.tourstore.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Object provides access to Tomcat connection pool
 */
public class ConnectionPoolFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPoolFactory.class);
    private static DataSource dataSource;

    static {
        try {
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/tourstore");
        } catch (NamingException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method returns connection from connection pool
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new SQLException("Unable to get connection: " + e.getMessage());
        }
        return connection;
    }
}
