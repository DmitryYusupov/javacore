package ru.yusdm.javacore.lesson7collectionsmap.homework.sortstrings;

import java.util.*;

public class SortStringsDemo {

    public static void main(String[] args) {
        StringSorter stringSorter = new StringSorterWithCompareToMethod();
        List<String> unsortedStrings = prepareStringsToSort();
        printList(stringSorter.getSorted(unsortedStrings));

        System.out.println("----------------------------------");
        unsortedStrings = prepareStringsToSort();
        stringSorter = new StringSortedWithNotEffectiveAlgorithm();
        printList(stringSorter.getSorted(unsortedStrings));
    }

    private static List<String> prepareStringsToSort() {
        List<String> result = new ArrayList<>();
        result.add("kotlin");
        result.add("in");
        result.add("Action");
        result.add("teaches");
        result.add("you");
        result.add("to");
        result.add("use");
        result.add("the");
        result.add("Kotlin");
        result.add("language");
        result.add("for");
        result.add("production");
        result.add("quality");
        result.add("applications");
        return result;
    }

    private static void printList(Collection<String> strings) {
        for (String s : strings) {
            System.out.println(s);
        }
    }
}


