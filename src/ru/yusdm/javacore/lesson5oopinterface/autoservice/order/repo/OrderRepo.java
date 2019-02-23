package ru.yusdm.javacore.lesson5oopinterface.autoservice.order.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.domain.Order;

public interface OrderRepo {

    void add(Order order);

    Order findById(long id);

    void deleteById(long id);

    void delete(Order order);

    void printAll();
}
