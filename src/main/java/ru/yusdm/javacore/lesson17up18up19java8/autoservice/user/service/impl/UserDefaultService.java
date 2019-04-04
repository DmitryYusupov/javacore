package ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.service.impl;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.search.UserSearchCondition;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.service.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDefaultService implements UserService {

    private final UserRepo userRepo;
    private final OrderService orderService;

    public UserDefaultService(UserRepo userRepo, OrderService orderService) {
        this.userRepo = userRepo;
        this.orderService = orderService;
    }

    @Override
    public User insert(User user) {
        if (user != null) {
            userRepo.insert(user);
        }

        return user;
    }

    @Override
    public void insert(Collection<User> users) {
        if (users != null && !users.isEmpty()) {
            userRepo.insert(users);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        if (id != null) {
            return userRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(User user) {
        if (user.getId() != null) {
            this.deleteById(user.getId());
        }
    }

    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return userRepo.findById(searchCondition.getId()).map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            return userRepo.search(searchCondition);
        }
    }

    @Override
    public void update(User user) {
        if (user.getId() != null) {
            userRepo.update(user);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public int countAll() {
        return userRepo.countAll();
    }
}