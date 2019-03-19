package ru.yusdm.javacore.lesson12xml.autoservice.user.service.impl;

import ru.yusdm.javacore.lesson12xml.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson12xml.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson12xml.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson12xml.autoservice.user.search.UserSearchCondition;
import ru.yusdm.javacore.lesson12xml.autoservice.user.service.UserService;

import java.util.List;

public class UserDefaultService implements UserService {

    private final UserRepo userRepo;
    private final OrderService orderService;

    public UserDefaultService(UserRepo userRepo, OrderService orderService) {
        this.userRepo = userRepo;
        this.orderService = orderService;
    }

    @Override
    public void insert(User user) {
        if (user != null) {
            userRepo.insert(user);
        }
    }

    @Override
    public User findById(Long id) {
        if (id != null) {
            return userRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(User user) {
        if (user.getId() != null) {
            this.deleteById(user.getId());
        }
    }

    @Override
    public List<User> search(UserSearchCondition searchCondition) {
        return userRepo.search(searchCondition);
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
}