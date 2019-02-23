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
    public User findById(long id) {
        return userRepo.findById(id);
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }

}