package ru.yusdm.javacore.lesson9genericsbegin.autoservice.order.service;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

}
