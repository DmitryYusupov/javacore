package ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.search.OrderSearchCondition;

import java.sql.Connection;
import java.util.List;

public interface OrderRepo extends BaseRepo<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

    int countByModel(long modelId);

    int countByMark(long markId);

    void deleteByUserId(long userId);

    List<Order> findByUserId(long userId);

    void deleteByIdTx(long id, Connection connection);

    Order insertTx(Order order, Connection connection);
}
