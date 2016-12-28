package ru.innopolis.tourstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.tourstore.db.DatabaseConnector;
import ru.innopolis.tourstore.entity.Order;
import ru.innopolis.tourstore.exception.OrderDaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.tourstore.db.SQLConstants.*;

/**
 * Object represents DAO for order entity
 */
public class OrderDao extends AbstractDao<Order> {
    private static final Logger LOG = LoggerFactory.getLogger(OrderDao.class);
    private DatabaseConnector databaseConnector;

    public OrderDao(){
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Methods retrieves all orders from the Orders table
     * @throws OrderDaoException in case of SQL exception
     */
    public List<Order> getAll() throws OrderDaoException {
        List<Order> orders = new ArrayList<>();
        try{
            ResultSet resultSet = databaseConnector.getStatement().executeQuery(SELECT_ALL_ORDERS_QUERY);

            while (resultSet.next()){
                Order order = new Order();

                order.setId(resultSet.getInt("ID"));
                order.setUserId(resultSet.getInt("USER_ID"));
                order.setTourId(resultSet.getInt("TOUR_ID"));

                orders.add(order);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new OrderDaoException(e.getMessage());
        }
        return orders;
    }

    /**
     * Methods returns Order object by ID from the Orders table
     * @throws OrderDaoException in case of SQL exception
     */
    public Order getEntityById(int id) throws OrderDaoException {
        Order order = new Order();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseConnector.getPrepareStatement(SELECT_ORDER_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                order.setId(resultSet.getInt("ID"));
                order.setUserId(resultSet.getInt("USER_ID"));
                order.setTourId(resultSet.getInt("TOUR_ID"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new OrderDaoException(e.getMessage());
        } finally {
            if(preparedStatement != null){
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
        return order;
    }

    /**
     * Methods update the existing record
     * @throws OrderDaoException in case of SQL exception
     */
    public void update(Order entity) throws OrderDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(UPDATE_ORDER_QUERY);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getTourId());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new OrderDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
    }

    /**
     * Method deletes the record by the ID
     * @throws OrderDaoException in case of SQL exception
     */
    public void delete(int id) throws OrderDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(DELETE_ORDER_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new OrderDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
    }

    /**
     * Method creates new order record
     * @throws OrderDaoException in case of SQL exception
     */
    public void create(Order entity) throws OrderDaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = databaseConnector.getPrepareStatement(INSERT_ORDER_QUERY);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getTourId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new OrderDaoException(e.getMessage());
        }finally {
            if (preparedStatement != null) {
                databaseConnector.closePreparedStatement(preparedStatement);
            }
        }
    }
}
