package ru.innopolis.tourstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.tourstore.db.IDatabaseConnector;
import ru.innopolis.tourstore.db.SQLConstants;
import ru.innopolis.tourstore.entity.Order;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.OrderDaoException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.servlets.PasswordAuthentication;

import java.sql.*;

public class TestDBConnector implements IDatabaseConnector {
    private static final Logger LOG = LoggerFactory.getLogger(TestDBConnector.class);

    private static TestDBConnector dbConnector = null;
    private Connection connection;
    private Statement statement;

    private TestDBConnector(){
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(SQLConstants.DATABASE_URL_H2_TEST);
            statement = connection.createStatement();
            statement.execute(SQLConstants.CREATE_TABLE_USERS_QUERY);
            statement.execute(SQLConstants.CREATE_TABLE_TOURS_QUERY);
            statement.execute(SQLConstants.CREATE_TABLE_ORDERS_QUERY);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static TestDBConnector getInstance(){
        if(dbConnector == null){
            return new TestDBConnector();
        }
        return dbConnector;
    }

    @Override
    public Statement getStatement() {
        return statement;
    }

    @Override
    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return preparedStatement;
    }

    @Override
    public void closePreparedStatement(PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
