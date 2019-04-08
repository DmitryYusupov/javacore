package ru.yusdm.javacore.lesson21regularexpressions.autoservice.order.repo;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

    int countByModel(long modelId);

    int countByMark(long markId);

    void deleteByUserId(long userId);

    List<Order> findByUserId(long userId);
}
