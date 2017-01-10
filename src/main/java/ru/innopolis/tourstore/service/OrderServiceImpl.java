package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.dao.OrderDao;
import ru.innopolis.tourstore.entity.Order;
import ru.innopolis.tourstore.entity.Tour;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.OrderDaoException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.exception.UserDaoException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private UserService userService;
    private TourService tourService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao){
        this.orderDao = orderDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public List<Order> getAll() throws OrderDaoException{
        return orderDao.getAll();
    }

    @Override
    public Order getEntityById(int id) throws OrderDaoException {
        return orderDao.getEntityById(id);
    }

    @Override
    public void update(Order entity) throws OrderDaoException {
        orderDao.update(entity);
    }

    @Override
    public void delete(int id) throws OrderDaoException {
        orderDao.delete(id);
    }

    @Override
    public void create(Order entity) throws OrderDaoException {
        orderDao.create(entity);
    }

    @Override
    public String getOrderInfo(Order order) throws UserDaoException, TourDaoException {
        User user = userService.getEntityById(order.getUserId());
        Tour tour = tourService.getEntityById(order.getTourId());
        return "User \"" + user.getName() + "\"" + " " + " ---- " + tour.getName();
    }

    @Override
    public List<String> getOrderInfos(List<Order> orders) throws UserDaoException, TourDaoException {
        List<String> orderInfos = new ArrayList<>();
        for(Order order : orders){
            orderInfos.add(getOrderInfo(order));
        }
        return orderInfos;
    }
}
