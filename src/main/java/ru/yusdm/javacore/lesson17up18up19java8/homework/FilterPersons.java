package ru.yusdm.javacore.lesson17up18up19java8.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FilterPersons {

    private static class Person {
        private String name;
        private String lastName;
        private int age;

        public Person(String name, String lastName, int age) {
            this.name = name;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println("----Before java 8 ----------");
        demoBeforeJava8();
        System.out.println("----With java 8 ----------");
        demoWithJava8();

        System.out.println("----With java 8 with predicats ----------");
        demoWithJava8WithPredicates();
    }

    private static void demoWithJava8WithPredicates() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Ivan", "Ivanov", 5));
        persons.add(new Person("Ivan", "Ivanov", 10));
        persons.add(new Person("Ivan", "Ivanov", 15));
        persons.add(new Person("Dmitry", "Ivanov", 20));
        persons.add(new Person("Dmitry", "Ivanov", 25));


        List<Predicate<Person>> filterConditions = Arrays.asList(
                (p) -> p.age > 15,
                (p) -> p.lastName.equals("Ivanov"),
                (p) -> p.name.equals("Dmitry")
        );

        List<Person> filtered = filter(persons, (p) -> {
            boolean result = true;
            for (Predicate<Person> condition : filterConditions) {
                result = condition.test(p);
                if (!result) {
                    break;
                }
            }
            return result;
        });

        for (Person p : filtered) {
            System.out.println(p);
        }
    }

    private static void demoWithJava8() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Ivan", "Ivanov", 5));
        persons.add(new Person("Ivan", "Ivanov", 10));
        persons.add(new Person("Ivan", "Ivanov", 15));
        persons.add(new Person("Dmitry", "Ivanov", 20));
        persons.add(new Person("Dmitry", "Ivanov", 25));

        List<Person> filtered = filter(persons, (p) -> p.age > 15);
        filter(filtered, (p) -> p.lastName.equals("Ivanov"));
        filtered = filter(filtered, (p) -> p.name.equals("Dmitry"));

        for (Person p : filtered) {
            System.out.println(p);
        }
    }

    private static List<Person> filter(List<Person> persons, Function<Person, Boolean> filter) {
        List<Person> filtered = new ArrayList<>();

        for (Person person : persons) {
            if (filter.apply(person)) {
                filtered.add(person);
            }
        }
        return filtered;
    }


    private static void demoBeforeJava8() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Ivan", "Ivanov", 5));
        persons.add(new Person("Ivan", "Ivanov", 10));
        persons.add(new Person("Ivan", "Ivanov", 15));
        persons.add(new Person("Dmitry", "Ivanov", 20));
        persons.add(new Person("Dmitry", "Ivanov", 25));

        List<Person> filtered = filterByAge(persons, 15);
        filterByLastName(filtered, "Ivanov");
        filtered = filterByName(filtered, "Dmitry");

        for (Person p : filtered) {
            System.out.println(p);
        }
    }

    private static List<Person> filterByAge(List<Person> persons, int age) {
        List<Person> filtered = new ArrayList<>();

        for (Person person : persons) {
            if (person.age > age) {
                filtered.add(person);
            }
        }
        return filtered;
    }

    private static List<Person> filterByName(List<Person> persons, String name) {
        List<Person> filtered = new ArrayList<>();

        for (Person person : persons) {
            if (person.name.equals(name)) {
                filtered.add(person);
            }
        }
        return filtered;
    }

    private static List<Person> filterByLastName(List<Person> persons, String lastName) {
        List<Person> filtered = new ArrayList<>();

        for (Person person : persons) {
            if (person.lastName.equals(lastName)) {
                filtered.add(person);
            }
        }
        return filtered;
    }
}
