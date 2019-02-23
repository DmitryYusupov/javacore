package ru.yusdm.javacore.lesson5oopinterface.autoservice.order.repo.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.repo.OrderRepo;

import static ru.yusdm.javacore.lesson5oopinterface.autoservice.storage.Storage.orders;


public class OrderMemoryRepo implements OrderRepo {

    private int orderIndex = -1;

    @Override
    public void add(Order order) {
        if (orderIndex == orders.length - 1) {
            Order[] newArrOrders = new Order[orders.length * 2];
            System.arraycopy(orders, 0, newArrOrders, 0, orders.length);
            orders = newArrOrders;
        }

        orderIndex++;
        orders[orderIndex] = order;
    }

    @Override
    public Order findById(long id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return orders[orderIndex];
        }

        return null;
    }

    @Override
    public void deleteById(long id) {
        Integer orderIndex = findOrderIndexById(id);

        if (orderIndex != null) {
            deleteOrderByIndex(orderIndex);
        }
    }

    private void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(orders, index);
        orderIndex--;
    }

    @Override
    public void printAll() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private Integer findOrderIndexById(Long orderId) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId().equals(orderId)) {
                return i;
            }
        }
        return null;
    }

}
