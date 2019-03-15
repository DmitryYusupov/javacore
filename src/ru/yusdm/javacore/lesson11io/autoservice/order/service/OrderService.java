package ru.yusdm.javacore.lesson11io.autoservice.order.service;

import ru.yusdm.javacore.lesson11io.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson11io.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson11io.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

    void deleteByUserId(Long userId);
}
