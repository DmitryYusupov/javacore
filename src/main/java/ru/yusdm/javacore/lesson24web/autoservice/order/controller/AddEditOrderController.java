package ru.yusdm.javacore.lesson24web.autoservice.order.controller;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.mark.dto.MarkDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.ProxyModel;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.dto.UserDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.user.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AddEditOrderController", urlPatterns = "/addeditorder")
public class AddEditOrderController extends BaseController {

    private OrderService orderService = ServiceSupplier.getInstance().getOrderService();
    private MarkService markService = ServiceSupplier.getInstance().getMarkService();
    private UserService userService = ServiceSupplier.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("users", UserDtoConverter.convertToDtos(userService.findAll()));
            req.setAttribute("marks", MarkDtoConverter.convertToDtos(markService.findAll()));

            if (isParamExists(req, "id")) {
                setRequestAttributesIfEditOrder(req, resp);
            }

            forwardToPage(req, resp, "addeditorder.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Error while prepare data to create order", resp);
        }
    }

    private void setRequestAttributesIfEditOrder(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        Long orderId = Long.parseLong(request.getParameter("id"));
        Optional<Order> order = orderService.findById(orderId);

        if (order.isPresent()) {
            request.setAttribute("editedOrder", order.get());
        } else {
            //no such order
            resp.sendRedirect("orders");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (isParamExists(req, "editedOrderId")) {
                updateOrder(req);
            } else {
                addNewOrder(req);
            }

            resp.sendRedirect("orders");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Error while add/edit order", resp);
        }
    }

    private void addNewOrder(HttpServletRequest req) {
        Order order = createOrderFromRequest(req);
        orderService.insert(order);
    }

    private void updateOrder(HttpServletRequest req) {
        Order order = createOrderFromRequest(req);
        order.setId(Long.parseLong(req.getParameter("editedOrderId")));
        orderService.update(order);
    }

    private Order createOrderFromRequest(HttpServletRequest req) {
        Order order = new Order();
        order.setPrice(Integer.parseInt(req.getParameter("price")));
        order.setDescription(req.getParameter("description"));
        order.setUser(new User(Long.parseLong(req.getParameter("userId"))));
        order.setMark(new Mark(Long.parseLong(req.getParameter("markId"))));
        order.setModel(new ProxyModel(Long.parseLong(req.getParameter("modelId"))));

        return order;
    }
}