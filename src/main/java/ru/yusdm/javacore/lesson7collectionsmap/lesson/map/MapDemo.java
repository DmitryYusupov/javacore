package ru.yusdm.javacore.lesson7collectionsmap.lesson.map;



import ru.yusdm.javacore.lesson5oopinterface.lesson.enumtest.complexenum.Person;

import java.util.*;


/**
 * Created by Admin on 2/27/2019.
 */
public class MapDemo {

    public static void main(String[] args) {
        // simpleHashMap();
        //handleNpe();
        //demoLoopMap();
        //putElemDemoWithoutHashCodeRewrite();
        demoLoopLinkedMap();
    }

    private static void simpleHashMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("Size " + map.size());
        System.out.println("Map has 'map.containsKey(\"one\")' " + map.containsKey("one"));
        System.out.println("Map has 'map.containsValue(3)' " + map.containsValue(3));
        System.out.println("Map.get() " + map.get("one"));
        System.out.println("Map.get if not exists " + map.get("assas"));

        Integer nullValue = null;
        //map.get("any").equals(nullValue);
        nullValue.equals(map.get("any"));
    }

    private static void handleNpe() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        Integer nullValue = null;
        if (map.get("NotExisting") != null) {
            map.get("NotExisting").equals(nullValue);
        }

        System.out.println("Get or default " + map.getOrDefault("one", 500));
        System.out.println("Get or default2 " + map.getOrDefault("NotExisting", 500));
    }


    private static void demoLoopMap(){
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        map.forEach((key23, value44554)->{
            System.out.println("Key " + key23 + "; " + value44554);
        });
    }

    private static void demoLoopLinkedMap(){
        Map<Person, Integer> map = new LinkedHashMap<>();
        map.put(new Person(), 1);
        map.put(new Person(), 2);
        map.put(new Person(), 3);
        map.put(new Person(), 4);

        map.forEach((key23, value44554)->{
            System.out.println("Key " + key23 + "; " + value44554);
        });
    }

    private static void putElemDemoWithoutHashCodeRewrite(){
        Map<Person, String> map = new HashMap<>();
        map.put(new Person(), "AA");
        map.put(new Person(), "BB");
        map.put(new Person(), "CC");
        map.put(new Person(), "DD");
        map.put(new Person(), "EE");


        map.forEach((k,v)->{
            System.out.println(k.hashCode() + " " + v);
        });
    }


}
