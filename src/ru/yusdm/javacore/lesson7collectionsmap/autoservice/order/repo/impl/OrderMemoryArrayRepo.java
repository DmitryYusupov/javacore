package ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.repo.impl;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.search.OrderSearchCondition;

import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson6collectionlist.autoservice.storage.Storage.ordersArray;


public class OrderMemoryArrayRepo implements OrderRepo {
    private static final Order[] EMPTY_ORDERS_ARR = new Order[0];
    private int orderIndex = -1;

    @Override
    public void add(Order order) {
        if (orderIndex == ordersArray.length - 1) {
            Order[] newArrOrders = new Order[ordersArray.length * 2];
            System.arraycopy(ordersArray, 0, newArrOrders, 0, ordersArray.length);
            ordersArray = newArrOrders;
        }

        orderIndex++;
        ordersArray[orderIndex] = order;
    }

    @Override
    public Order findById(long id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return ordersArray[orderIndex];
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
        ArrayUtils.removeElement(ordersArray, index);
        orderIndex--;
    }

    @Override
    public void printAll() {
        for (Order order : ordersArray) {
            if (order != null) {
                System.out.println(order);
            }
        }
    }

    private Integer findOrderIndexById(long orderId) {
        for (int i = 0; i < ordersArray.length; i++) {
            if (ordersArray[i] != null && Long.valueOf(orderId).equals(ordersArray[i].getId())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return Collections.emptyList();
    }
}
