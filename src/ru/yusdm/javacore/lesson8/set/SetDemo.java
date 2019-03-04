package ru.yusdm.javacore.lesson8.set;

import java.util.*;

/**
 * Created by Admin on 3/4/2019.
 */
public class SetDemo {

    public static void main(String[] args) {
        //       makeItUniqueDemo();
        //demoContains();
        demoOrder();
    }

    private static void simpleDemo() {
        Set<String> setStr = new HashSet<>();
        System.out.println(setStr.add("a"));
        System.out.println(setStr.add("a"));
        System.out.println(setStr.add("d"));
        for (String s : setStr) {
            System.out.println(s);
        }
    }

    private static void makeItUniqueDemo() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("b");
        System.out.println(list.size());

        Set<String> setStr = new HashSet<>();
        for (String s : list) {
            setStr.add(s);
        }

        System.out.println(setStr);
        System.out.println("--------------");
        setStr = new HashSet<>(list);
        System.out.println(setStr);
    }

    private static void demoContains() {
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println("Contains a " + set.contains("a"));
    }

    private static void demoOrder() {
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c"));
        set.add("asdf");
        set.add("wwwasdf");
        set.add("rrasdf");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("------Linked HashSet-------");
        set = new LinkedHashSet<>(Arrays.asList("a", "b", "c"));
        set.add("asdf");
        set.add("wwwasdf");
        set.add("rrasdf");

        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("------TreeSet-------");
        set = new TreeSet<>(Arrays.asList("a", "b", "c"));
        set.add("asdf");
        set.add("wwwasdf");
        set.add("rrasdf");

        for (String s : set) {
            System.out.println(s);
        }
    }

}
