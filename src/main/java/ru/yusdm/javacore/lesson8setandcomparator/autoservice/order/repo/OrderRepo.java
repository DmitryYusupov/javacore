package ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.repo;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo {

    void add(Order order);

    Order findById(long id);

    List<Order> search(OrderSearchCondition searchCondition);
}
