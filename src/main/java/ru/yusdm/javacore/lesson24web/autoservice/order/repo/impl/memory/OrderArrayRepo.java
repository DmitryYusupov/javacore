package ru.yusdm.javacore.lesson24web.autoservice.order.repo.impl.memory;

import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.utils.OptionalUtils;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson24web.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson24web.autoservice.storage.SequenceGenerator;

import java.sql.Connection;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static ru.yusdm.javacore.lesson24web.autoservice.storage.Storage.ordersArray;


public class OrderArrayRepo implements OrderRepo {

    private int orderIndex = -1;

    @Override
    public Order insert(Order order) {
        if (orderIndex == ordersArray.length - 1) {
            Order[] newArrOrders = new Order[ordersArray.length * 2];
            System.arraycopy(ordersArray, 0, newArrOrders, 0, ordersArray.length);
            ordersArray = newArrOrders;
        }

        orderIndex++;
        order.setId(SequenceGenerator.getNextValue());
        ordersArray[orderIndex] = order;

        return order;
    }

    @Override
    public void insert(Collection<Order> orders) {
        orders.forEach(this::insert);
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public Optional<Order> findById(Long id) {
        return findOrderIndexById(id).map(index -> ordersArray[index]);
    }

    @Override
    public void deleteById(Long id) {
        findOrderIndexById(id).ifPresent(this::deleteOrderByIndex);
    }

    private void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(ordersArray, index);
        orderIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(ordersArray).filter(Objects::nonNull).forEach(System.out::println);
    }

    private Optional<Integer> findOrderIndexById(long orderId) {
        OptionalInt optionalInt = IntStream.range(0, ordersArray.length).filter(i ->
                ordersArray[i] != null && Long.valueOf(orderId).equals(ordersArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return emptyList();
    }

    @Override
    public int countByModel(long modelId) {
        return (int) Arrays.stream(ordersArray).filter(order -> modelId == order.getModel().getId()).count();
    }

    @Override
    public int countByMark(long markId) {
        return (int) Arrays.stream(ordersArray).filter(order -> markId == order.getMark().getId()).count();
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
        return Arrays.stream(ordersArray).filter(order -> order.getUser().getId().equals(userId)).collect(toList());
    }

    @Override
    public void deleteByIdTx(long id, Connection connection) {
        deleteById(id);
    }

    @Override
    public Order insertTx(Order order, Connection connection) {
        return insert(order);
    }

    @Override
    public int countAll() {
        return ordersArray.length;
    }
}
