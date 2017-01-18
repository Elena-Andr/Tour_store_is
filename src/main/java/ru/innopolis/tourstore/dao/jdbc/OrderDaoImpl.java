package ru.innopolis.tourstore.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.tourstore.dao.OrderDao;
import ru.innopolis.tourstore.db.IDatabaseConnector;
import ru.innopolis.tourstore.entity.OrderEntity;
import ru.innopolis.tourstore.exception.OrderDaoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import static ru.innopolis.tourstore.db.SQLConstants.*;

/**
 * Object represents DAO for order entity
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOG = LoggerFactory.getLogger(OrderDaoImpl.class);

    private IDatabaseConnector databaseConnector;

    @Autowired
    public OrderDaoImpl(IDatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
    }

    /**
     * Method retrieves all orders from the Orders table
     * @throws OrderDaoException in case of SQL exception
     */
    @Override
    public List<OrderEntity> getAll() throws OrderDaoException {
        List<OrderEntity> orders = new ArrayList<>();
        try{
            ResultSet resultSet = databaseConnector.getStatement().executeQuery(SELECT_ALL_ORDERS_QUERY);

            while (resultSet.next()){
                OrderEntity order = new OrderEntity();

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
     * Method returns Order object by ID from the Orders table
     * @throws OrderDaoException in case of SQL exception
     */
    @Override
    public OrderEntity getEntityById(int id) throws OrderDaoException {
        OrderEntity order = new OrderEntity();
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
     * Method updates the existing record
     * @throws OrderDaoException in case of SQL exception
     */
    @Override
    public void update(OrderEntity entity) throws OrderDaoException {
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
    @Override
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
    @Override
    public void create(OrderEntity entity) throws OrderDaoException {
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
