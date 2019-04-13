package ru.yusdm.javacore.lesson24web.autoservice.order.controller;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.dto.OrderDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@WebServlet(name = "ViewDetailedOrder", urlPatterns = "/vieworder")
public class ViewDetailsOrderController extends BaseController {

    private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (isParamExists(req, "id")) {
                long id = Long.parseLong(req.getParameter("id"));

                Optional<Order> fullOrder = orderService.getFullOrder(id);
                if (fullOrder.isPresent()) {
                    req.setAttribute("order", OrderDtoConverter.convertToDto(fullOrder.get()));
                } else {
                    show404("No order with id '" + id + "'");
                }
            }
            forwardToPage(req, resp, "order.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            show500(e.getMessage());
        }
    }
}
