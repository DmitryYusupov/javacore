package ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.repo.impl.memory;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.storage.SequenceGenerator;

import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson8setandcomparator.autoservice.storage.Storage.ordersArray;


public class OrderArrayRepo implements OrderRepo {
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
        order.setId(SequenceGenerator.getNextValue());
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
