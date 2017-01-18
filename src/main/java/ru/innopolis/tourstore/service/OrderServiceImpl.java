package ru.innopolis.tourstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.dao.OrderDao;
import ru.innopolis.tourstore.entity.OrderEntity;
import ru.innopolis.tourstore.entity.TourEntity;
import ru.innopolis.tourstore.entity.UserEntity;
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
    public OrderServiceImpl(@Qualifier("orderDaoImplHibernate") OrderDao orderDao){
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
    public List<OrderEntity> getAll() throws OrderDaoException{
        return orderDao.getAll();
    }

    @Override
    public OrderEntity getEntityById(int id) throws OrderDaoException {
        return orderDao.getEntityById(id);
    }

    @Override
    public void update(OrderEntity entity) throws OrderDaoException {
        orderDao.update(entity);
    }

    @Override
    public void delete(int id) throws OrderDaoException {
        orderDao.delete(id);
    }

    @Override
    public void create(OrderEntity entity) throws OrderDaoException {
        orderDao.create(entity);
    }

    @Override
    public String getOrderInfo(OrderEntity order) throws UserDaoException, TourDaoException {
        UserEntity user = userService.getEntityById(order.getUserId());
        TourEntity tour = tourService.getEntityById(order.getTourId());
        return "User \"" + user.getName() + "\"" + " " + " ---- " + tour.getName();
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
