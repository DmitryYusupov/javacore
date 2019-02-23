package ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo.UserRepo;

import static ru.yusdm.javacore.lesson5oopinterface.autoservice.storage.Storage.users;


public class UserMemoryRepo implements UserRepo {

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
    public void delete(User user) {
        Integer foundIndex = findUserIndexByEntity(user);

        if (foundIndex != null) {
            deleteUserByIndex(foundIndex);
        }
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
            System.out.println(user);
        }
    }

    private Integer findUserIndexByEntity(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(user)) {
                return i;
            }
        }

        return null;
    }

    private Integer findUserIndexById(Long userId) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId().equals(userId)) {
                return i;
            }
        }
        return null;
    }

}
