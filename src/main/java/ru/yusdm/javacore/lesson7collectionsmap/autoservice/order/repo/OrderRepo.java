package ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.repo;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo {

    void add(Order order);

    Order findById(long id);

    List<Order> search(OrderSearchCondition searchCondition);
}
