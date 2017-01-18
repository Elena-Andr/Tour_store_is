package ru.innopolis.tourstore.dao;

import ru.innopolis.tourstore.entity.OrderEntity;
import ru.innopolis.tourstore.exception.OrderDaoException;

import java.util.List;

public interface OrderDao {

    List<OrderEntity> getAll() throws OrderDaoException;
    OrderEntity getEntityById(int id) throws OrderDaoException;
    void update(OrderEntity entity) throws OrderDaoException;
    void delete(int id) throws OrderDaoException;
    void create(OrderEntity entity) throws OrderDaoException;
}
