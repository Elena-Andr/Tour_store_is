package ru.innopolis.tourstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.tourstore.dao.OrderDao;
import ru.innopolis.tourstore.dao.OrderDaoImpl;
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
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserService userService;

    @Autowired
    private TourService tourService;

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
        User user = null;
        Tour tour = null;
        try {
            user = userService.getEntityById(order.getUserId());
            tour = tourService.getEntityById(order.getTourId());
        } catch (UserDaoException e) {
            LOG.error(e.getMessage(), e);
            throw new UserDaoException(e.getMessage());
        } catch (TourDaoException e) {
            LOG.error(e.getMessage(), e);
            throw new TourDaoException(e.getMessage());
        }
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
