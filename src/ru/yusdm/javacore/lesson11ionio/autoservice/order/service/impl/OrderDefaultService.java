package ru.yusdm.javacore.lesson11ionio.autoservice.order.service.impl;

import ru.yusdm.javacore.lesson11ionio.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson11ionio.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson11ionio.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson11ionio.autoservice.order.service.OrderService;

import java.util.Collections;
import java.util.List;

public class OrderDefaultService implements OrderService {

    private final OrderRepo orderRepo;

    public OrderDefaultService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void insert(Order order) {
        if (order != null) {
            orderRepo.insert(order);
        }
    }

    @Override
    public void update(Order order) {
        if (order.getId() != null) {
            orderRepo.update(order);
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
    public void deleteByUserId(Long userId) {
        if (userId != null) {
            orderRepo.deleteByUserId(userId);
        }
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        if (userId != null) {
            return orderRepo.findByUserId(userId);
        }

        return Collections.emptyList();
    }
}
