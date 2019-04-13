package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.service;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

    void deleteByUserId(Long userId);

    List<Order> getOrdersByUser(Long userId);

    void fillOrderWithDetailedData(Order order);

    List<Order> getAllOrdersWithFilledData();
}
