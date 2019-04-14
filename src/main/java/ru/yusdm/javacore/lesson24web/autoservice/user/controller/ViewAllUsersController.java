package ru.yusdm.javacore.lesson24web.autoservice.user.controller;

import org.apache.commons.collections4.CollectionUtils;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.dto.UserDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.user.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ViewAllUsers", urlPatterns = "/users")
public class ViewAllUsersController extends BaseController {

    private UserService userService = ServiceSupplier.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<User> users = userService.findAll();

            if (CollectionUtils.isNotEmpty(users)) {
                req.setAttribute("users", UserDtoConverter.convertToDtos(users));
            }

            forwardToPage(req, resp, "users.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Couldn't fetch all user!");
        }
    }
}
