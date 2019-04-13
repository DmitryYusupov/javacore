package ru.yusdm.javacore.lesson24web.autoservice.order.service;

import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

    void deleteByUserId(Long userId);

    List<Order> getOrdersByUser(Long userId);

    void fillOrderWithDetailedData(Order order);

    List<Order> getAllOrdersWithFilledData();
}
