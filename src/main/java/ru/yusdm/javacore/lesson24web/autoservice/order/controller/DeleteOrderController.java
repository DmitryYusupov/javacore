package ru.yusdm.javacore.lesson24web.autoservice.order.controller;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteOrder", urlPatterns = "/deleteorder")
public class DeleteOrderController extends BaseController {

    private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            if (isParamExists(req, "id")) {
                long id = Long.parseLong(req.getParameter("id"));
                orderService.deleteById(id);
            }
            resp.sendRedirect("orders");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
