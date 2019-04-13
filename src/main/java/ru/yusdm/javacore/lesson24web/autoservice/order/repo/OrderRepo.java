package ru.yusdm.javacore.lesson24web.autoservice.order.repo;

import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.search.OrderSearchCondition;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface OrderRepo extends BaseRepo<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

    int countByModel(long modelId);

    int countByMark(long markId);

    void deleteByUserId(long userId);

    List<Order> findByUserId(long userId);

    void deleteByIdTx(long id, Connection connection);

    Order insertTx(Order order, Connection connection);

    Optional<Order> getFullOrder(long id);
}
