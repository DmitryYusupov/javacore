package ru.yusdm.javacore.lesson17java8.lesson.part2.zu;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Admin on 4/1/2019.
 */
public class C_Consumer {

    private static class Person {
        private int age;
        private String name;
        private String lastName;

        public Person() {
        }

        public Person(int age, String name, String lastName) {
            this.age = age;
            this.name = name;
            this.lastName = lastName;
        }
    }

    private static void prepareDataForUpperCase(Person p) {
        p.age = 11;
        p.name = "Ivan";
        p.lastName = "Ivanov";

        //sent user to google validator
        // uses last name
    }

    private static void prepareDataForLowerCase(Person p) {
        p.age = 11;
        p.name = "Ivan";
        p.lastName = "ivanov";

        //sent user to google validator
        // uses last name
    }

    private static void prepareCommonData(Person p, Consumer<Person> postPersonSettings) {
        p.age = 11;
        p.name = "Ivan";
        p.lastName = "Ivanov";

        if (postPersonSettings != null) {
            postPersonSettings.accept(p);
        }

        //sent user to google validator
        // uses last name
    }


    private static void toUpperCase(Person p) {
        p.lastName = p.lastName.toUpperCase();
    }

    private static void toLowerCase(Person p) {
        p.lastName = p.lastName.toLowerCase();
    }

    private static void testPersonUpperCase() {
        Person fact = new Person();
        prepareCommonData(fact, p -> p.lastName = "Ivanov");
        toUpperCase(fact);

        Person expected = new Person();
        expected.lastName = "IVANOV";

        System.out.println(expected.lastName.equals(fact.lastName));
    }

    private static void testPersonLowerCase() {
        Person fact = new Person();
        prepareCommonData(fact, p -> p.lastName = "Ivanov333");
        toLowerCase(fact);

        Person expected = new Person();
        expected.lastName = "ivanov";

        System.out.println(expected.lastName.equals(fact.lastName));
    }


    public static void main(String[] args) {
        // testPersonUpperCase();
        // testPersonLowerCase();

        List<Person> persons = Arrays.asList(
                new Person(1, "a", "a"),
                new Person(2, "b", "b")
        );


        modifyPersonList(persons, (p)
                -> {
            p.lastName = p.lastName.toUpperCase();
            p.name = p.name.toUpperCase();
        });

        modifyPersonList(persons, p -> p.age = p.age + 1);

        System.out.println();
    }

    private static void modifyPersonList(List<Person> persons, Consumer<Person> modifier) {
        for (Person person : persons) {
            if (modifier != null) {
                modifier.accept(person);
            }
        }
    }
}
