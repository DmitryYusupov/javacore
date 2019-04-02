package ru.yusdm.javacore.lesson17up18up19java8.autoservice.order.repo.impl.memory;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.Storage.ordersList;


public class OrderCollectionRepo implements OrderRepo {

    @Override
    public Order insert(Order order) {
        order.setId(SequenceGenerator.getNextValue());
        ordersList.add(order);

        return order;
    }

    @Override
    public void insert(Collection<Order> orders) {
        for (Order order : orders) {
            insert(order);
        }
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public Order findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        List<Order> orders = doSearch(searchCondition);

        if (!orders.isEmpty() && searchCondition.shouldPaginate()) {
            orders = getPageableData(orders, searchCondition.getPaginator());
        }

        return orders;
    }

    private List<Order> doSearch(OrderSearchCondition searchCondition) {
        return ordersList;
    }

    private List<Order> getPageableData(List<Order> orders, Paginator paginator) {
        return CollectionUtils.getPageableData(orders, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void deleteById(Long id) {
        Order found = findOrderById(id);

        if (found != null) {
            ordersList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Order order : ordersList) {
            System.out.println(order);
        }
    }

    private Order findOrderById(long orderId) {
        for (Order order : ordersList) {
            if (Long.valueOf(orderId).equals(order.getId())) {
                return order;
            }
        }
        return null;
    }

    @Override
    public int countByModel(long modelId) {
        int count = 0;
        for (Order order : ordersList) {
            if (modelId == order.getModel().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countByMark(long markId) {
        int count = 0;
        for (Order order : ordersList) {
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
        return ordersList;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> foundOrders = new ArrayList<>();

        for (Order order : ordersList) {
            if (order.getUser().getId().equals(userId)) {
                foundOrders.add(order);
            }
        }

        return foundOrders;
    }

    @Override
    public int countAll() {
        return ordersList.size();
    }
}
