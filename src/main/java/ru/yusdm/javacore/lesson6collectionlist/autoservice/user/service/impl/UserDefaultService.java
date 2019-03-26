package ru.yusdm.javacore.lesson6collectionlist.autoservice.user.service.impl;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.search.UserSearchCondition;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.service.UserService;

import java.util.List;

public class UserDefaultService implements UserService {

    private final UserRepo userRepo;

    public UserDefaultService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void add(User user) {
        if (user != null) {
            userRepo.add(user);
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
    public void deleteById(Long id) {
        if (id != null) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }

}