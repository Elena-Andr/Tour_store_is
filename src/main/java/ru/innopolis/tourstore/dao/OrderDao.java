package ru.innopolis.tourstore.dao;

import ru.innopolis.tourstore.entity.Order;
import ru.innopolis.tourstore.exception.OrderDaoException;

import java.util.List;

public interface OrderDao {

    List<Order> getAll() throws OrderDaoException;
    Order getEntityById(int id) throws OrderDaoException;
    void update(Order entity) throws OrderDaoException;
    void delete(int id) throws OrderDaoException;
    void create(Order entity) throws OrderDaoException;
}
