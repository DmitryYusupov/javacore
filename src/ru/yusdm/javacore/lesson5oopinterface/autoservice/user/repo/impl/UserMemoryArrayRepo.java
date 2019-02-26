package ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.search.UserSearchCondition;

import static ru.yusdm.javacore.lesson5oopinterface.autoservice.storage.Storage.users;


public class UserMemoryArrayRepo implements UserRepo {
    private static final User[] EMPTY_USERS_ARR = new User[0];
    private int userIndex = -1;

    @Override
    public void add(User user) {
        if (userIndex == users.length - 1) {
            User[] newArrUsers = new User[users.length * 2];
            System.arraycopy(users, 0, newArrUsers, 0, users.length);
            users = newArrUsers;
        }

        userIndex++;
        users[userIndex] = user;
    }

    @Override
    public User findById(long id) {
        Integer userIndex = findUserIndexById(id);
        if (userIndex != null) {
            return users[userIndex];
        }

        return null;
    }

    @Override
    public User[] search(UserSearchCondition searchCondition) {
        return EMPTY_USERS_ARR;
    }

    @Override
    public void deleteById(long id) {
        Integer userIndex = findUserIndexById(id);

        if (userIndex != null) {
            deleteUserByIndex(userIndex);
        }
    }

    private void deleteUserByIndex(int index) {
        ArrayUtils.removeElement(users, index);
        userIndex--;
    }

    @Override
    public void printAll() {
        for (User user : users) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    private Integer findUserIndexById(long userId) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && Long.valueOf(userId).equals(users[i].getId())) {
                return i;
            }
        }
        return null;
    }

}
