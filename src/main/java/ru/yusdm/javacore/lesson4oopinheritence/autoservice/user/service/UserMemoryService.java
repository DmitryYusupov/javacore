package ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.service;

import ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.User;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.repo.UserMemoryRepo;

public class UserMemoryService {

    private UserMemoryRepo userRepo = new UserMemoryRepo();

    public void addUser(User user) {
        userRepo.addUser(user);
    }

    public User findUserById(long id) {
        return userRepo.findUserById(id);
    }

    public void deleteUser(User user) {
        userRepo.deleteUser(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteUser(id);
    }

    public void printUsers() {
        userRepo.printUsers();
    }

}