package ru.yusdm.javacore.lesson8setandcomparator.homework;

import java.util.*;

public class CollectionsIntersection {

    public static void main(String[] args) {
        System.out.println("------------------Own realization-----");
        demoOwnCollectionInterSection();
        System.out.println("------------------Embedded lang features-----");
        demoEmbeddedLangFeatureToCalcIntersection();
    }

    private static void demoOwnCollectionInterSection() {
        List<String> list1 = Arrays.asList("a", "b", "c", "d", "e");
        List<String> list2 = Arrays.asList("c", "d", "c", "w");

        Set<String> intersection = new HashSet<>();
        Set<String> setWithList1Elems = new HashSet<>(list1);
        for (String str : list2) {
            boolean intersectedElement = !setWithList1Elems.add(str);
            if (intersectedElement) {
                intersection.add(str);
            }
        }

        printCollection(intersection);
    }

    private static void demoEmbeddedLangFeatureToCalcIntersection() {
        List<String> list1 = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        List<String> list2 = new ArrayList<>(Arrays.asList("c", "d", "c", "w"));

        list1.retainAll(list2);
        printCollection(list1);
    }

    private static void printCollection(Collection<String> collection) {
        for (String str : collection) {
            System.out.println(str);
        }
    }
}
