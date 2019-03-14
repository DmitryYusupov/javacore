package ru.yusdm.javacore.lesson11io.autoservice.order.repo;

import ru.yusdm.javacore.lesson11io.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson11io.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson11io.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

}
