package ru.innopolis.tourstore.service;

import ru.innopolis.tourstore.entity.OrderEntity;
import ru.innopolis.tourstore.exception.OrderDaoException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.exception.UserDaoException;

import java.util.List;

public interface OrderService {
    List<OrderEntity> getAll() throws OrderDaoException;
    OrderEntity getEntityById(int id) throws OrderDaoException;
    void update(OrderEntity entity) throws OrderDaoException;
    void delete(int id) throws OrderDaoException;
    void create(OrderEntity entity) throws OrderDaoException;
    String getOrderInfo(OrderEntity order) throws UserDaoException, TourDaoException;
    List<String> getOrderInfos(List<OrderEntity> orders) throws UserDaoException, TourDaoException;
}
