package ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.service;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService {

    void add(Order order);

    Order findById(Long id);

    void delete(Order order);

    List<Order> search(OrderSearchCondition searchCondition);

}
