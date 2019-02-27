package ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.repo.impl;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.search.UserSearchCondition;

import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson6collectionlist.autoservice.storage.Storage.usersList;

public class UserMemoryCollectionRepo implements UserRepo {

    @Override
    public void add(User user) {
        usersList.add(user);
    }

    @Override
    public User findById(long id) {
        return findUserById(id);
    }

    @Override
    public List<User> search(UserSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(long id) {
        User found = findUserById(id);

        if (found != null) {
            usersList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (User user : usersList) {
            System.out.println(user);
        }
    }

    private User findUserById(long userId) {
        for (User user : usersList) {
            if (Long.valueOf(userId).equals(user.getId())) {
                return user;
            }
        }
        return null;
    }
}