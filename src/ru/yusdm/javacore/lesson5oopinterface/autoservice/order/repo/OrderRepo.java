package ru.yusdm.javacore.lesson5oopinterface.autoservice.order.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.domain.Order;

public interface OrderRepo extends BaseRepo {

    void add(Order order);

    Order findById(long id);
}
