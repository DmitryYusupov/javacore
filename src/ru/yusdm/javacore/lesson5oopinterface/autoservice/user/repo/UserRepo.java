package ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.domain.User;

public interface UserRepo {
    void add(User user);

    User findById(long id);

    void delete(User user);

    void deleteById(long id);

    void printAll();
}
