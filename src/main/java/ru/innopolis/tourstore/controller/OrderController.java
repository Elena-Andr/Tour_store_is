package ru.innopolis.tourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.tourstore.entity.OrderEntity;
import ru.innopolis.tourstore.entity.TourEntity;
import ru.innopolis.tourstore.entity.UserEntity;
import ru.innopolis.tourstore.exception.OrderDaoException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.service.OrderService;
import ru.innopolis.tourstore.service.TourService;
import ru.innopolis.tourstore.service.UserService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class OrderController{

    private OrderService orderService;
    private UserService userService;
    private TourService tourService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, TourService tourService){
        this.orderService = orderService;
        this.userService = userService;
        this.tourService = tourService;
    }

    @RequestMapping("/store/order")
    public String getUserOrders(Model model,
                                HttpServletRequest request,
                                @RequestParam("id") int tourId)
            throws UserDaoException, OrderDaoException, TourDaoException {

        // Get signed in user
        String userName = request.getUserPrincipal().getName();
        UserEntity user = userService.getEntityByName(userName);
        TourEntity tour = tourService.getEntityById(tourId);

        //Add the tour to the user's orders
        OrderEntity order = new OrderEntity();
        order.setTourEntity(tour);
        order.setUserEntity(user);
        orderService.create(order);

        //Update user info
        user = userService.getEntityById(user.getId());
        List<OrderEntity> orders = user.getOrders();
        model.addAttribute("myOrders", orderService.getOrderInfos(orders));

        return "ShoppingCartView";
    }

    @RequestMapping("/stat")
    public String getAllOrders(Model model) throws UserDaoException, OrderDaoException, TourDaoException {
            List<OrderEntity> orders = orderService.getAll();
            List<String> orderInfos = orderService.getOrderInfos(orders);
            model.addAttribute("orders", orderInfos);
            return "OrdersView";
    }
}
