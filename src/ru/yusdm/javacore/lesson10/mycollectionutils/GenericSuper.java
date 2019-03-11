package ru.yusdm.javacore.lesson10.mycollectionutils;

import com.oracle.jrockit.jfr.Producer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/11/2019.
 */
public class GenericSuper {

    public static void main(String[] args) {
    }

    public void demoExtends(List<? extends Number> list) {
        List<? extends Number> num1 = new ArrayList<Number>();
        List<? extends Number> num2 = new ArrayList<Integer>();
        List<? extends Number> num3 = new ArrayList<Long>();
        List<? extends Number> num4 = null;

        /**
         list.add(1); //new ArrayList<Double>()
         list.add(1L);//new ArrayList<Integer>()
         list.add(1.);//new ArrayList<Integer>()
         Number number = null;
         list.add(number); //new ArrayList<Long>()
         */

        for (Number num : num3) {
            System.out.println(num);
        }
        /**
         for (Integer num : num4) {
         System.out.println(num);
         }
         */
    }

    public void zu() {
        List<? super Number> num1 = new ArrayList<Number>();
        List<? super Number> num2 = new ArrayList<Object>();
        List<? super Number> num3 = null;

        Integer i = null;
        num1.add(i);

        Long l = null;
        num1.add(l);

        Number n = null;
        num1.add(n);

        /*
        //errors
        Object o = null;
        num1.add(o);

        List<Number> nums = new ArrayList<>();
        nums.add(o);
        */

        List<? super Integer> ints1 = new ArrayList<Integer>();
        List<? super Integer> ints2 = new ArrayList<Number>();
        List<? super Integer> ints3 = new ArrayList<Object>();

        ints1.add(1);
        /** ints1.add(1L);
         ints1.add(1.);
         Object oo = null;
         ints1.add(oo);
         Number n = null;
         ints1.add(n);
         */
    }

    public void readSuper(List<? super Integer> ints3) {
        /*
        for (Integer i : ints3){

        }*/
        /*
        for (Number i : ints3){

        }
        */

        for (Object i : ints3) {

        }
/*
        P - Producer
                E - extends;
        C - Consumer
                S - super

                        Josh Bloch
                        */
    }


}
