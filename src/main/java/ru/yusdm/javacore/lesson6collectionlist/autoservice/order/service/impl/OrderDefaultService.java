package ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service.impl;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.repo.impl.OrderMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service.OrderService;

import java.util.Collections;
import java.util.List;

public class OrderDefaultService implements OrderService {

    private final OrderRepo orderRepo;

    public OrderDefaultService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(Order order) {
        if (order != null) {
            orderRepo.add(order);
        }
    }

    @Override
    public Order findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Order order) {
        if (order.getId() != null) {
            this.deleteById(order.getId());
        }
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return orderRepo.search(searchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            orderRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }
}
