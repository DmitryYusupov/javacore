package ru.yusdm.javacore.lesson4oopinheritence.autoservice.order.repo;

import ru.yusdm.javacore.lesson4oopinheritence.autoservice.common.ArrayUtils;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.order.Order;

import static ru.yusdm.javacore.lesson4oopinheritence.autoservice.storage.Storage.orders;

public class OrderMemoryRepo {

    private int orderIndex = -1;

    public void addOrder(Order order) {
        if (orderIndex == orders.length - 1) {
            Order[] newArrOrders = new Order[orders.length * 2];
            System.arraycopy(orders, 0, newArrOrders, 0, orders.length);
            orders = newArrOrders;
        }

        orderIndex++;
        orders[orderIndex] = order;
    }

    public Order findOrderById(long id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return orders[orderIndex];
        }

        return null;
    }

    public void deleteOrder(Order order) {
        Integer foundIndex = findOrderIndexByEntity(order);

        if (foundIndex != null) {
            deleteOrderByIndex(foundIndex);
        }
    }

    public void deleteOrder(long id) {
        Integer orderIndex = findOrderIndexById(id);

        if (orderIndex != null) {
            deleteOrderByIndex(orderIndex);
        }
    }

    private void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(orders, index);
        orderIndex--;
    }

    public void printOrders() {
        for (Order order : orders) {
            if (order != null) {
                System.out.println(order);
            }
        }
    }

    private Integer findOrderIndexByEntity(Order order) {
        for (int i = 0; i < orders.length; i++) {

            if (orders[i] != null && orders[i].equals(order)) {
                return i;
            }
        }

        return null;
    }

    private Integer findOrderIndexById(long orderId) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && Long.valueOf(orderId).equals(orders[i].getId())) {
                return i;
            }
        }
        return null;
    }

}
