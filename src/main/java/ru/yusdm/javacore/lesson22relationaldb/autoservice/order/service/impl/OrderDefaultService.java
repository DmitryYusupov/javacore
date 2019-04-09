package ru.yusdm.javacore.lesson22relationaldb.autoservice.order.service.impl;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.service.OrderService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class OrderDefaultService implements OrderService {

    private final OrderRepo orderRepo;

    public OrderDefaultService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Order insert(Order order) {
        if (order != null) {
            orderRepo.insert(order);
        }

        return order;
    }

    @Override
    public void insert(Collection<Order> orders) {
        if (isNotEmpty(orders)) {
            orderRepo.insert(orders);
        }
    }

    @Override
    public void update(Order order) {
        if (order.getId() != null) {
            orderRepo.update(order);
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        } else {
            return Optional.empty();
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
        if (searchCondition.getId() != null) {
            return orderRepo.findById(searchCondition.getId()).map(Collections::singletonList).orElse(emptyList());
        } else {
            return orderRepo.search(searchCondition);
        }
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

        return emptyList();
    }

    @Override
    public int countAll() {
        return orderRepo.countAll();
    }
}
