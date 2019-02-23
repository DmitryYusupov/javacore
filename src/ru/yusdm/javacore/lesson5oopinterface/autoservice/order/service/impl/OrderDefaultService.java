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
    public Order findById(long id) {
        return orderRepo.findById(id);
    }

    @Override
    public void delete(Order order) {
        orderRepo.delete(order);
    }

    @Override
    public void deleteById(long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }
}
