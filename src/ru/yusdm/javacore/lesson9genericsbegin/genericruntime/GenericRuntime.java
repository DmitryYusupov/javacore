package ru.yusdm.javacore.lesson9genericsbegin.genericruntime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/6/2019.
 */
public class GenericRuntime {

    public static void main(String[] args) {
        List<String> listWithStrings = new ArrayList<>();
        List<Integer> listWithInts = new ArrayList<>();

        System.out.println(listWithStrings.getClass());
        System.out.println(listWithInts.getClass());

        List<Integer> ints = new ArrayList<>();
        ints.add(1);

        System.out.println(ints.get(0).getClass());

        List<Object> something = new ArrayList<>();
        something.add(1);
        something.add("ss");

        System.out.println(something.get(0).getClass());
    }


}
