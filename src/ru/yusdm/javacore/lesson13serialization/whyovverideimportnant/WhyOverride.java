package ru.yusdm.javacore.lesson13serialization.whyovverideimportnant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/22/2019.
 */
public class WhyOverride {

    private interface X extends Y{
    }

    private interface Y {
    }

    private static class Z implements X {
    }

    private static void test22(X x) {
    }

    private static void test22(Y y) {
    }

    public static void main(String[] args) {
        test22(new Z());

        List<A> list = new ArrayList<>();
        list.add(new A());
        list.add(new B());

        for (A a : list) {
            a.test();
        }

    }

    private static class A {

        public void test() {
            System.out.println("I am A");
        }
    }

    public static class B extends A {

        @Override
        public void test() {
            System.out.println("I am B");
        }
    }


}
