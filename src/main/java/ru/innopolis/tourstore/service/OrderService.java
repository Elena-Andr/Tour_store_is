package ru.innopolis.tourstore.service;

import ru.innopolis.tourstore.entity.Order;
import ru.innopolis.tourstore.exception.OrderDaoException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.exception.UserDaoException;

import java.util.List;

public interface OrderService {
    List<Order> getAll() throws OrderDaoException;
    Order getEntityById(int id) throws OrderDaoException;
    void update(Order entity) throws OrderDaoException;
    void delete(int id) throws OrderDaoException;
    void create(Order entity) throws OrderDaoException;
    String getOrderInfo(Order order) throws UserDaoException, TourDaoException;
    List<String> getOrderInfos(List<Order> orders) throws UserDaoException, TourDaoException;
}
