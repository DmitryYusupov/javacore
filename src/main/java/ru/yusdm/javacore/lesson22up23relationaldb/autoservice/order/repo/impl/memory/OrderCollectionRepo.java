package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.repo.impl.memory;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.storage.SequenceGenerator;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static ru.yusdm.javacore.lesson22up23relationaldb.autoservice.storage.Storage.ordersList;


public class OrderCollectionRepo implements OrderRepo {

    @Override
    public Order insert(Order order) {
        order.setId(SequenceGenerator.getNextValue());
        ordersList.add(order);

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
        findOrderById(id).map(order -> ordersList.remove(order));
    }

    @Override
    public void printAll() {
        ordersList.forEach(System.out::println);
    }

    private Optional<Order> findOrderById(long orderId) {
        return ordersList.stream().filter(order -> Long.valueOf(orderId).equals(order.getId())).findAny();
    }

    @Override
    public int countByModel(long modelId) {
        return (int) ordersList.stream().filter(order -> modelId == order.getModel().getId()).count();
    }

    @Override
    public int countByMark(long markId) {
        return (int) ordersList.stream().filter(order -> markId == order.getMark().getId()).count();
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
        return ordersList.stream().filter(order -> order.getUser().getId().equals(userId)).collect(toList());
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
        return ordersList.size();
    }
}
