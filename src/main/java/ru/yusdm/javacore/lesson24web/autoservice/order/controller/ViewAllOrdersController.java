package ru.yusdm.javacore.lesson24web.autoservice.order.controller;


import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.dto.OrderDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@WebServlet(name = "ViewAllOrders", urlPatterns = "/orders")
public class ViewAllOrdersController extends BaseController {

    private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Order> orders = orderService.search(getSearchConditionFromRequest(req));
            if (isNotEmpty(orders)) {
                req.setAttribute("orders", OrderDtoConverter.convertToDtos(orders));
            }
            forwardToPage(req, resp, "orders.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Couldn't fetch orders!", resp);
        }
    }

    private OrderSearchCondition getSearchConditionFromRequest(HttpServletRequest request) {
        OrderSearchCondition searchCondition = new OrderSearchCondition();
        searchCondition.setPaginator(createPaginatorFromRequest(request));
        return searchCondition;
    }

}
