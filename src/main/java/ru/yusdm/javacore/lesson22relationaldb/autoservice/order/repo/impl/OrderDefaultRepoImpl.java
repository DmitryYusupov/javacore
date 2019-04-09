package ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.impl;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.search.OrderSearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class OrderDefaultRepoImpl implements OrderRepo {
    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return null;
    }

    @Override
    public int countByModel(long modelId) {
        return 0;
    }

    @Override
    public int countByMark(long markId) {
        return 0;
    }

    @Override
    public void deleteByUserId(long userId) {

    }

    @Override
    public List<Order> findByUserId(long userId) {
        return null;
    }

    @Override
    public Order insert(Order entity) {
        return null;
    }

    @Override
    public void insert(Collection<Order> items) {

    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
