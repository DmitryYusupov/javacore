package ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage;


import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.order.domain.Order;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final int CAPACITY = 3;
    public static Mark[] marksArray = new Mark[CAPACITY];
    public static Model[] modelsArray = new Model[CAPACITY];
    public static Order[] ordersArray = new Order[CAPACITY];
    public static User[] usersArray = new User[CAPACITY];

    public static List<Mark> marksList = new ArrayList<>();
    public static List<Model> modelsList = new ArrayList<>();
    public static List<Order> ordersList = new ArrayList<>();
    public static List<User> usersList = new ArrayList<>();

}
