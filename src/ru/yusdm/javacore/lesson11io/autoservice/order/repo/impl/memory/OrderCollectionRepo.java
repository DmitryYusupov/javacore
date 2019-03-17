package ru.yusdm.javacore.lesson11io.autoservice.order.repo.impl.memory;

import ru.yusdm.javacore.lesson11io.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson11io.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson11io.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson11io.autoservice.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson11io.autoservice.storage.Storage.ordersList;


public class OrderCollectionRepo implements OrderRepo {

    @Override
    public void insert(Order order) {
        order.setId(SequenceGenerator.getNextValue());
        ordersList.add(order);
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public Order findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
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

    @Override
    public int countByModel(long modelId) {
        int count = 0;
        for (Order order : ordersList) {
            if (modelId == order.getModel().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countByMark(long markId) {
        int count = 0;
        for (Order order : ordersList) {
            if (markId == order.getMark().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void deleteByUserId(long userId) {
    }

    @Override
    public List<Order> findAll() {
        return ordersList;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> foundOrders = new ArrayList<>();

        for (Order order : ordersList) {
            if (order.getUser().getId().equals(userId)) {
                foundOrders.add(order);
            }
        }

        return foundOrders;
    }
}
