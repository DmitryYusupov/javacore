package ru.yusdm.javacore.lesson17up18up19java8.lesson.part2.predefinedfuncinterfaces;

import java.util.function.Function;

/**
 * Created by Admin on 4/1/2019.
 */
public class E_Map {

    private static class Person{
        private String name;

        public Person(String name) {
            this.name = name;
        }
    }

    private static<T> T convertPerson(Person p, Function<Person, T> convert){
        return convert.apply(p);
    }

    public static void main(String[] args) {
        String personStr = convertPerson(new Person("Ivan"),
                p -> "Person as str");
        Integer personInt = convertPerson(new Person("Ivan"),
                p -> 33);

        Person newPerson = convertPerson(new Person("Ivan"),
                p -> {
                    p.name = p.name.toLowerCase();
                    return p;
                }
        );

        System.out.println();
    }
}
