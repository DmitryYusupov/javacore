package ru.yusdm.javacore.lesson17up18up19java8.lesson.part4;

import java.util.function.Supplier;

/**
 * Created by Admin on 4/5/2019.
 */
public class B_GenericCreateObject {

    private static class Person {
        private String name;

        public Person() {
        }

        public Person(String name) {
            this.name = name;
            System.out.println("dsdsds");
        }
    }

    public static void main(String[] args) {
        Person entity = createEntity(Person::new);
        Person entity2 = createEntity(() -> new Person("ljlj"));
    }

    public static <T> T createEntity(Supplier<T> supplier) {
        T t = supplier.get();
        System.out.println("Instnance created");
        return t;
    }

    /*
    public static <T> T createEntity(){
        T t = new T();
        return t;
    }*/


}
