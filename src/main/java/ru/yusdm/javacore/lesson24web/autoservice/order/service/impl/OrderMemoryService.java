package ru.yusdm.javacore.lesson24web.autoservice.order.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.database.datasource.HikariCpDataSource;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.jdbc.SqlError;
import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson24web.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson24web.autoservice.order.search.OrderSearchCondition;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.repo.UserRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

public class OrderMemoryService implements OrderService {

    private final OrderRepo orderRepo;
    private final MarkRepo markRepo;
    private final ModelRepo modelRepo;
    private final UserRepo userRepo;

    public OrderMemoryService(OrderRepo orderRepo,
                               MarkRepo markRepo, ModelRepo modelRepo,
                               UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.markRepo = markRepo;
        this.modelRepo = modelRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Order insert(Order order) {
        if (order != null) {
            orderRepo.insert(order);
        }

        return order;
    }

    @Override
    public void insert(Collection<Order> orders) {
        if (isNotEmpty(orders)) {
            orderRepo.insert(orders);
        }
    }

    @Override
    public void update(Order order) {
        orderRepo.deleteById(order.getId());
        orderRepo.insert(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> getFullOrder(Long id) {
        if (id != null) {
            return orderRepo.getFullOrder(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Order order) {
        if (order.getId() != null) {
            this.deleteById(order.getId());
        }
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        List<Order> orders;
        if (searchCondition.getId() != null) {
            orders = orderRepo.findById(searchCondition.getId()).map(Collections::singletonList).orElse(emptyList());
        } else {
            orders = orderRepo.search(searchCondition);
        }

        if (CollectionUtils.isNotEmpty(orders)) {
            orders.forEach(this::fillOrderWithDetailedData);
        }

        return orders;
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            orderRepo.deleteById(id);
        }
    }

    @Override
    public void deleteByUserId(Long userId) {
        if (userId != null) {
            orderRepo.deleteByUserId(userId);
        }
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        if (userId != null) {
            return orderRepo.findByUserId(userId);
        }

        return emptyList();
    }

    @Override
    public int countAll() {
        return orderRepo.countAll();
    }

    @Override
    public List<Order> getAllOrdersWithFilledData() {
        List<Order> orders = findAll();
        orders.forEach(this::fillOrderWithDetailedData);
        return orders;
    }

    @Override
    public void fillOrderWithDetailedData(Order order) {
        long markId = order.getMark().getId();
        long modelId = order.getModel().getId();
        long userId = order.getUser().getId();

        Optional<Mark> mark = markRepo.findById(markId);
        if (!mark.isPresent()) {
            throw new RuntimeException("No such mark " + markId);
        }
        order.setMark(mark.get());

        Optional<Model> model = modelRepo.findById(modelId);
        if (!model.isPresent()) {
            throw new RuntimeException("No such model " + markId);
        }
        order.setModel(model.get());

        Optional<User> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("No such user " + markId);
        }
        order.setUser(user.get());
    }
}

