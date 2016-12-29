package ru.innopolis.tourstore.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

import static org.junit.Assert.assertEquals;


public class DaoTest {
    private static final Logger LOG = LoggerFactory.getLogger(DaoTest.class);
    private IDatabaseConnector dbConnector;

    @Before
    public void setUp(){
        try {
            dbConnector = TestDBConnector.getInstance();

            fillToursTable();
            fillUsersTable();
            fillOrdersTable();

        } catch (OrderDaoException e) {
            LOG.error(e.getMessage(), e);
        } catch (TourDaoException e) {
            LOG.error(e.getMessage(), e);
        } catch (UserDaoException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @After
    public void cleanUp(){
        try {
            dbConnector.getStatement().execute(SQLConstants.DROP_ALL_QUERY);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void successTest(){
        try {
            OrderDao orderDao = new OrderDao(dbConnector);
            List<Order> orderList = orderDao.getAll();

            String result = orderDao.getOrderInfo(orderList.get(0));
            String expected = "User \"Maria\"  ---- Italy";

            assertEquals(expected, result);
        } catch (OrderDaoException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test(expected = Exception.class)
    public void failedTest() throws Exception {
            OrderDao orderDao = new OrderDao(null);
            orderDao.delete(666);
    }

    private void fillToursTable() throws TourDaoException {

        TourDao tourDao = new TourDao(dbConnector);
        Tour tour = new Tour();
        tour.setName("Italy");
        tour.setDescription("Sample Italy tour description");

        tourDao.create(tour);

        tour.setName("Germany");
        tour.setDescription("Sample Germany tour description");
        tourDao.create(tour);

        tour.setName("France");
        tour.setDescription("Sample France tour description");
        tourDao.create(tour);
    }

    private void fillUsersTable() throws UserDaoException {

        UserDao userDao = new UserDao(dbConnector);
        User user = new User();
        user.setName("Maria");
        user.setRole("user");

        String password = "123";
        byte[] hashedPassword = PasswordAuthentication.hashPassword(password, new byte[]{});
        user.setPassword(hashedPassword);

        userDao.create(user);

        user.setName("admin");
        user.setRole("admin");
        user.setPassword(hashedPassword);
        userDao.create(user);
    }

    private void fillOrdersTable() throws OrderDaoException {

        OrderDao orderDao = new OrderDao(dbConnector);
        Order order = new Order();
        order.setUserId(1);
        order.setTourId(1);

        orderDao.create(order);
    }
}