package ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.repo.impl;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.search.UserSearchCondition;

import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson7collectionsmap.autoservice.storage.Storage.usersArray;


public class UserMemoryArrayRepo implements UserRepo {
    private int userIndex = -1;

    @Override
    public void add(User user) {
        if (userIndex == usersArray.length - 1) {
            User[] newArrUsers = new User[usersArray.length * 2];
            System.arraycopy(usersArray, 0, newArrUsers, 0, usersArray.length);
            usersArray = newArrUsers;
        }

        userIndex++;
        usersArray[userIndex] = user;
    }

    @Override
    public User findById(long id) {
        Integer userIndex = findUserIndexById(id);
        if (userIndex != null) {
            return usersArray[userIndex];
        }

        return null;
    }

    @Override
    public List<User> search(UserSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(long id) {
        Integer userIndex = findUserIndexById(id);

        if (userIndex != null) {
            deleteUserByIndex(userIndex);
        }
    }

    private void deleteUserByIndex(int index) {
        ArrayUtils.removeElement(usersArray, index);
        userIndex--;
    }

    @Override
    public void printAll() {
        for (User user : usersArray) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    private Integer findUserIndexById(long userId) {
        for (int i = 0; i < usersArray.length; i++) {
            if (usersArray[i] != null && Long.valueOf(userId).equals(usersArray[i].getId())) {
                return i;
            }
        }
        return null;
    }

}
