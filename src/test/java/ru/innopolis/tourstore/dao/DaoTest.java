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
import ru.innopolis.tourstore.filter.PasswordAuthentication;

import static org.junit.Assert.assertEquals;


public class DaoTest {
    private static final Logger LOG = LoggerFactory.getLogger(DaoTest.class);
    private IDatabaseConnector dbConnector;
    private OrderDao orderDaoImpl;
    private TourDao tourDaoImpl;
    private UserDao userDaoImpl;

    @Before
    public void setUp(){
        try {
            dbConnector = TestDBConnector.getInstance();

            orderDaoImpl = new OrderDaoImpl(dbConnector);
            tourDaoImpl = new TourDaoImpl(dbConnector);
            userDaoImpl = new UserDaoImpl(dbConnector);

            fillToursTable();
            fillUsersTable();
            fillOrdersTable();

        } catch (OrderDaoException | TourDaoException | UserDaoException e) {
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
            List<Order> orders = orderDaoImpl.getAll();
            User user = userDaoImpl.getEntityById(orders.get(0).getUserId());
            Tour tour = tourDaoImpl.getEntityById(orders.get(0).getTourId());
            String expected = "User \"Maria\"  ---- Italy";
            String result = "User \"" + user.getName() + "\"" + " " + " ---- " + tour.getName();

            assertEquals(expected, result);
        } catch (OrderDaoException | UserDaoException | TourDaoException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test(expected = TourDaoException.class)
    public void failedTest() throws TourDaoException {
        Tour tour = new Tour();
        tour.setName("qwertyuiop[';lkjhgfdssxcvbnm,.ppflgokrtkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        tourDaoImpl.create(tour);
    }

    private void fillToursTable() throws TourDaoException {
        Tour tour = new Tour();
        tour.setName("Italy");
        tour.setDescription("Sample Italy tour description");
        tourDaoImpl.create(tour);

        tour.setName("Germany");
        tour.setDescription("Sample Germany tour description");
        tourDaoImpl.create(tour);

        tour.setName("France");
        tour.setDescription("Sample France tour description");
        tourDaoImpl.create(tour);
    }

    private void fillUsersTable() throws UserDaoException {

        User user = new User();
        user.setName("Maria");
        user.setRole("user");
        user.setSalt(PasswordAuthentication.generateSalt());

        String password = "123";
        String hashedPassword = PasswordAuthentication.hashPassword(password,user.getSalt());
        user.setPassword(hashedPassword);

        userDaoImpl.create(user);

        user.setName("admin");
        user.setRole("admin");
        user.setPassword(hashedPassword);
        userDaoImpl.create(user);
    }

    private void fillOrdersTable() throws OrderDaoException {

        Order order = new Order();
        order.setUserId(1);
        order.setTourId(1);

        orderDaoImpl.create(order);
    }
}