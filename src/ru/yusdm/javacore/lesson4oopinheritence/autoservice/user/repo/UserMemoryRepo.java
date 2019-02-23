package ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.repo;

import ru.yusdm.javacore.lesson4oopinheritence.autoservice.common.ArrayUtils;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.User;

import static ru.yusdm.javacore.lesson4oopinheritence.autoservice.storage.Storage.users;

public class UserMemoryRepo {

    private int userIndex = -1;

    public void addUser(User user) {
        if (userIndex == users.length - 1) {
            User[] newArrUsers = new User[users.length * 2];
            System.arraycopy(users, 0, newArrUsers, 0, users.length);
            users = newArrUsers;
        }

        userIndex++;
        users[userIndex] = user;
    }

    public User findUserById(long id) {
        Integer userIndex = findUserIndexById(id);
        if (userIndex != null) {
            return users[userIndex];
        }

        return null;
    }

    public void deleteUser(User user) {
        Integer foundIndex = findUserIndexByEntity(user);

        if (foundIndex != null) {
            deleteUserByIndex(foundIndex);
        }
    }

    public void deleteUser(Long id) {
        Integer userIndex = findUserIndexById(id);

        if (userIndex != null) {
            deleteUserByIndex(userIndex);
        }
    }

    private void deleteUserByIndex(int index) {
        ArrayUtils.removeElement(users, index);
        userIndex--;
    }

    private User[] getNotNullUsers() {
        User[] newArrUsers = new User[userIndex];


        int i = 0;
        for (User user : users) {
            if (user != null) {
                newArrUsers[i] = user;
                i++;
            }
        }

        userIndex = users.length - 1;
        return newArrUsers;
    }

    public void printUsers() {
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
