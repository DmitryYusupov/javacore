package ru.yusdm.javacore.lesson5oopinterface.autoservice.order.service.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.repo.impl.OrderMemoryRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.service.OrderService;

public class OrderDefaultService implements OrderService {

    private final OrderMemoryRepo orderRepo;

    public OrderDefaultService(OrderMemoryRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(Order order) {
        orderRepo.add(order);
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
