package ru.yusdm.javacore.lesson4oopinheritence.autoservice.storage;


import ru.yusdm.javacore.lesson4oopinheritence.autoservice.mark.Mark;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.model.Model;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.order.Order;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.User;

public class Storage {
    private static final int CAPACITY = 3;

    private Mark[] marks = new Mark[CAPACITY];
    private int lastMarkIndex = -1;

    private Model[] models = new Model[CAPACITY];
    private int lastModelIndex = -1;

    private Order[] orders = new Order[CAPACITY];
    private int lastOrderIndex = -1;

    private User[] users = new User[CAPACITY];
    private int lastUserIndex = -1;

    /**
     * User API
     */
    public void addUser(User user) {
        if (lastUserIndex == users.length - 1) {
            User[] newArrUsers = new User[this.users.length * 2];
            System.arraycopy(users, 0, newArrUsers, 0, users.length);
            this.users = newArrUsers;
        }

        lastUserIndex++;
        users[lastUserIndex] = user;
    }

    public User findUserById(long id) {
        Integer userIndex = findUserIndexById(id);
        if (userIndex != null) {
            return users[userIndex];
        }

        return null;
    }

    public void deleteUser(User user) {
        Integer userIndex = findUserIndexByEntity(user);

        if (userIndex != null) {
            deleteUserByIndex(userIndex);
            lastUserIndex--;
        }
    }

    public void deleteUserById(Long id) {
        Integer userIndex = findUserIndexById(id);

        if (userIndex != null) {
            deleteUserByIndex(userIndex);
        }
    }

    private void deleteUserByIndex(int index) {
        User[] newArrUsers = new User[users.length];
        System.arraycopy(users, 0, newArrUsers, 0, index - 1);
        System.arraycopy(users, index, newArrUsers, index - 1, users.length - index);
        users = newArrUsers;
        lastUserIndex--;
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
