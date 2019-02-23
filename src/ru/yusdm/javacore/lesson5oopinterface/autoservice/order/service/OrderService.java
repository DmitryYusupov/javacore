package ru.yusdm.javacore.lesson5oopinterface.autoservice.order.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.domain.Order;

public interface OrderService {

    void add(Order order);

    Order findById(long id);

    void delete(Order order);

    void deleteById(long id);

    void printAll();

}
