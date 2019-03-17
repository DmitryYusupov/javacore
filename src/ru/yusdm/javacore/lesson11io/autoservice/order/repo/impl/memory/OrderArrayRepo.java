package ru.yusdm.javacore.lesson11io.autoservice.order.repo.impl.memory;

import ru.yusdm.javacore.lesson11io.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson11io.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson11io.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson11io.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson11io.autoservice.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson11io.autoservice.storage.Storage.ordersArray;


public class OrderArrayRepo implements OrderRepo {

    private int orderIndex = -1;

    @Override
    public void insert(Order order) {
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
    public void update(Order entity) {

    }

    @Override
    public Order findById(Long id) {
        Integer orderIndex = findOrderIndexById(id);
        if (orderIndex != null) {
            return ordersArray[orderIndex];
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
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

    @Override
    public int countByModel(long modelId) {
        int count = 0;
        for (Order order : ordersArray) {
            if (modelId == order.getModel().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countByMark(long markId) {
        int count = 0;
        for (Order order : ordersArray) {
            if (markId == order.getMark().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void deleteByUserId(long userId) {
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(Arrays.asList(ordersArray));
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> foundOrders = new ArrayList<>();

        for (Order order : ordersArray) {
            if (order.getUser().getId().equals(userId)) {
                foundOrders.add(order);
            }
        }

        return foundOrders;
    }
}
