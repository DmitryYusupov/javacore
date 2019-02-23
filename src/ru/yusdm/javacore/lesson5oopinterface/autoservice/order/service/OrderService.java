package ru.yusdm.javacore.lesson5oopinterface.autoservice.order.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.domain.Order;

public interface OrderService extends BaseService {

    void add(Order order);

    Order findById(long id);

    void delete(Order order);

}
