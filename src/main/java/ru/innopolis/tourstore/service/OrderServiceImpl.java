package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.entity.OrderEntity;
import ru.innopolis.tourstore.exception.OrderDaoException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderEntity> getAll() throws OrderDaoException{
        return (List)orderRepository.findAll();
    }

    @Override
    public OrderEntity getEntityById(int id) throws OrderDaoException {
        return orderRepository.findOne(id);
    }

    @Override
    public void update(OrderEntity entity) throws OrderDaoException {
        orderRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(int id) throws OrderDaoException {
        orderRepository.delete(id);
    }

    @Override
    public void create(OrderEntity entity) throws OrderDaoException {
        orderRepository.saveAndFlush(entity);
    }

    @Override
    public String getOrderInfo(OrderEntity order) throws UserDaoException, TourDaoException {
        return "User \"" + order.getUserEntity().getName() + "\"" + " " + " ---- " + order.getTourEntity().getName();
    }

    @Override
    public List<String> getOrderInfos(List<OrderEntity> orders) throws UserDaoException, TourDaoException {
        List<String> orderInfos = new ArrayList<>();
        for(OrderEntity order : orders){
            orderInfos.add(getOrderInfo(order));
        }
        return orderInfos;
    }
}
