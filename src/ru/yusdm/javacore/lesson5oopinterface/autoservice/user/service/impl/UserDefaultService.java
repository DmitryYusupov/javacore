package ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo.impl.UserMemoryRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service.UserService;

public class UserDefaultService implements UserService {

    private final UserRepo userRepo;

    public UserDefaultService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void add(User user) {
        userRepo.add(user);
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