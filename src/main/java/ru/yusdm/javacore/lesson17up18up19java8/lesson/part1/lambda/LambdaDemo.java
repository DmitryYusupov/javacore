package ru.yusdm.javacore.lesson17up18up19java8.lesson.part1.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Admin on 3/29/2019.
 */
public class LambdaDemo {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("String");
            }
        };

        Runnable runnable2 = () -> System.out.println("Hello");

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("String");
                System.out.println("String2");
            }
        };

        Runnable runnable4 = () -> {
            System.out.println("Hello");
            System.out.println("Hello2");
        };
        new Thread(() -> System.out.println("Hello")).start();

        List<String> list = new ArrayList<>();
        list.add("z");
        list.add("y");
        list.add("x");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        list.sort((String s1, String s2) -> {
            return s1.compareTo(s2);
        });

        list.sort((s1, s2) -> {
            return s1.compareTo(s2);
        });

        list.sort((s1, s2) -> s1.compareTo(s2));
    }

}
