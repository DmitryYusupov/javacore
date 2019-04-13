package ru.yusdm.javacore.lesson24web.autoservice.order.controller;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.mark.dto.MarkDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson24web.autoservice.order.dto.OrderDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.dto.UserDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddEditOrderController", urlPatterns = "/addeditorder")
public class AddEditOrderController extends BaseController {

    private OrderService orderService = ServiceSupplier.getInstance().getOrderService();
    private MarkService markService = ServiceSupplier.getInstance().getMarkService();
    private UserService userService = ServiceSupplier.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try{
            List<Mark> marks = markService.findAll();
            List<User> users = userService.findAll();

            req.setAttribute("users", UserDtoConverter.convertToDtos(users));
            req.setAttribute("marks", MarkDtoConverter.convertToDtos(marks));

            forwardToPage(req, resp, "addeditorder.jsp");
        }catch (Exception e){
            e.printStackTrace();
            show500("Error while prepare data to create order");
        }
    }
}
