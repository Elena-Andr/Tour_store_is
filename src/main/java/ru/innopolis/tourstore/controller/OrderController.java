package ru.innopolis.tourstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.tourstore.entity.Order;
import ru.innopolis.tourstore.entity.User;
import ru.innopolis.tourstore.exception.OrderDaoException;
import ru.innopolis.tourstore.exception.TourDaoException;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.service.OrderService;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.function.Predicate;

@Controller
public class OrderController extends AbstractController{

    @Autowired
    private OrderService orderService;

    @RequestMapping("/store/order")
    public String getUserOrders(Model model, HttpSession session, @RequestParam("id") int tourId){
        try {
            //Add the tour to the user's orders
            User user = (User) session.getAttribute("user");

            Order order = new Order();
            order.setTourId(tourId);
            order.setUserId(user.getId());

            orderService.create(order);

            //Show all orders of the user
            List<Order> orders = orderService.getAll();
            Predicate<Order> orderPredicate = (Order o) -> o.getUserId() != user.getId();
            orders.removeIf(orderPredicate);
            model.addAttribute("myOrders", orderService.getOrderInfos(orders));

            return "ShoppingCartView";
        } catch (OrderDaoException e) {
            return handleError(model, e.getMessage());
        } catch (UserDaoException e) {
            return handleError(model, e.getMessage());
        } catch (TourDaoException e) {
            return handleError(model, e.getMessage());
        }
    }

    @RequestMapping("/stat")
    public String getAllOrders(Model model){
        try {
            List<Order> orders = orderService.getAll();
            List<String> orderInfos = orderService.getOrderInfos(orders);
            model.addAttribute("orders", orderInfos);
            return "OrdersView";
        } catch (OrderDaoException e) {
            return handleError(model, e.getMessage());
        } catch (UserDaoException e) {
            return handleError(model, e.getMessage());
        } catch (TourDaoException e) {
            return handleError(model, e.getMessage());
        }
    }
}
