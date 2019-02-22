package ru.yusdm.javacore.lesson4oopinheritence.autoservice.order.repo;

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

    public void deleteOrder(Long id) {
        Integer orderIndex = findOrderIndexById(id);

        if (orderIndex != null) {
            deleteOrderByIndex(orderIndex);
        }
    }

    private void deleteOrderByIndex(int index) {
        Order[] newArrOrders = new Order[orders.length];
        System.arraycopy(orders, 0, newArrOrders, 0, index - 1);
        System.arraycopy(orders, index, newArrOrders, index - 1, orders.length - index);
        orders = newArrOrders;
        orderIndex--;
    }

    public void printOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private Integer findOrderIndexByEntity(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(order)) {
                return i;
            }
        }

        return null;
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
