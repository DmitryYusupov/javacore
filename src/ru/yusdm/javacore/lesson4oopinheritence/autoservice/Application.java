package ru.yusdm.javacore.lesson4oopinheritence.autoservice;


import ru.yusdm.javacore.lesson4oopinheritence.autoservice.storage.Storage;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.User;

public class Application {

    public static void main(String[] args) {

        Storage storage = new Storage();

        for (int i = 0; i < 10; i++) {
            storage.addUser(new User((long) i, "Name_" + i, "LastName_" + i));
        }

        System.out.println("------USERS-------------");
        storage.printUsers();

        System.out.println("------USERS AFTER DELETE-------------");
        User p = storage.findUserById(3);
        storage.deleteUser(p);
        storage.printUsers();
    }
}
