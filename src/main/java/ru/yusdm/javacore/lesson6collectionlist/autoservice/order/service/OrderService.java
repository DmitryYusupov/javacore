package ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService {

    void add(Order order);

    Order findById(Long id);

    void delete(Order order);

    List<Order> search(OrderSearchCondition searchCondition);

}
