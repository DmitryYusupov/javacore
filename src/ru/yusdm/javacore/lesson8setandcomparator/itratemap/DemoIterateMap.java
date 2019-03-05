package ru.yusdm.javacore.lesson8setandcomparator.itratemap;

import java.util.*;

/**
 * Created by Admin on 3/4/2019.
 */
public class DemoIterateMap {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        map.forEach((key, value) -> {
            System.out.println("Key " + key + "; Value " + value);
        });
        System.out.println("-----Demo with set-------------");
        System.out.println("Get all keys");
        System.out.println(map.keySet());

        System.out.println(map.values());
        System.out.println(map.values().getClass());

        System.out.println("---Demo get all keys and values----");
        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println("Entry key: " + entry.getKey() + "; entry getValue " + entry.getValue());
        }

        for (Map.Entry<String, Integer> entry : entries) {
            if (new HashSet<>(Arrays.asList("a", "b")).contains(entry.getKey())) {
               // map.remove(entry.getKey());
            }
        }

        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            if (new HashSet<>(Arrays.asList("a", "b")).contains(next.getKey())) {
                iterator.remove();
            }
        }
        System.out.println(map.size());

    }
}
