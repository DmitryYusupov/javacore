package ru.yusdm.javacore.lesson5oopinterface.autoservice.storage;


import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.domain.User;

public class Storage {
    private static final int CAPACITY = 3;
    public static Mark[] marks = new Mark[CAPACITY];
    public static Model[] models = new Model[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static User[] users = new User[CAPACITY];
}
