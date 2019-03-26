package ru.yusdm.javacore.lesson4oopinheritence.autoservice.order.service;

import ru.yusdm.javacore.lesson4oopinheritence.autoservice.order.Order;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.order.repo.OrderMemoryRepo;

public class OrderMemoryService {

    private OrderMemoryRepo orderRepo = new OrderMemoryRepo();

    public void addOrder(Order order) {
        orderRepo.addOrder(order);
    }

    public Order findOrderById(long id) {
        return orderRepo.findOrderById(id);
    }

    public void deleteOrder(Order order) {
        orderRepo.deleteOrder(order);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteOrder(id);
    }

    public void printOrders() {
        orderRepo.printOrders();
    }
}
