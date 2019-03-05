package ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.repo.impl;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.storage.SequenceGenerator;

import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson8setandcomparator.autoservice.storage.Storage.ordersList;


public class OrderMemoryCollectionRepo implements OrderRepo {

    @Override
    public void add(Order order) {
        order.setId(SequenceGenerator.getNextValue());
        ordersList.add(order);
    }

    @Override
    public Order findById(long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(long id) {
        Order found = findOrderById(id);

        if (found != null) {
            ordersList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Order order : ordersList) {
            System.out.println(order);
        }
    }

    private Order findOrderById(long orderId) {
        for (Order order : ordersList) {
            if (Long.valueOf(orderId).equals(order.getId())) {
                return order;
            }
        }
        return null;
    }
}
