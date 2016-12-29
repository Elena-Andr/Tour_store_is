package ru.innopolis.tourstore.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.tourstore.dao.TourDao;
import ru.innopolis.tourstore.dao.UserDao;
import ru.innopolis.tourstore.db.DatabaseConnector;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.exception.UserDaoException;

/**
 * Object represents entity "Order"
 */
public class Order {
    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    private int id;
    private int userId;
    private int tourId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }
}
