package ru.yusdm.javacore.lesson4oopinheritence.autoservice.storage;


import ru.yusdm.javacore.lesson4oopinheritence.autoservice.mark.Mark;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.model.Model;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.order.Order;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.User;

public class Storage {
    private static final int CAPACITY = 3;
    public static Mark[] marks = new Mark[CAPACITY];
    public static Model[] models = new Model[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static User[] users = new User[CAPACITY];
}
