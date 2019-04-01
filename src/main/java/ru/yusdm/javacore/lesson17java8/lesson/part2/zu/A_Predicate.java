package ru.yusdm.javacore.lesson17java8.lesson.part2.zu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Admin on 4/1/2019.
 */
public class A_Predicate {

    private static class Person {
        private int age;
        private String name;
        private String lastName;
    }

    @FunctionalInterface
    private interface Match<T> {
        boolean match(T t);
    }
/*
    private static List<Person> filterPerson(List<Person> persons, Match<Person> match){
        List<Person> result = new ArrayList<>();

        for (Person p : persons) {
            if (match.match(p)) {
                result.add(p);
            }
        }

        return result;
    }*/

    private static List<Person> filterPerson(List<Person> persons, Predicate<Person> match){
        List<Person> result = new ArrayList<>();

        for (Person p : persons) {
            if (match.test(p)) {
                result.add(p);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        //age > 30
        /*filterPerson(persons, new Match<Person>() {
            @Override
            public boolean match(Person person) {
                return person.age > 30;
            }
        });*/
        //age > 30
        filterPerson(persons, person -> person.age > 30);
        filterPerson(persons, person -> person.name.length() > 2);
        filterPerson(persons, person -> person.lastName.length() > 2);
    }

    private static List<Person> filterIfAgeMoreThan30(List<Person> persons) {
        List<Person> result = new ArrayList<>();

        for (Person p : persons) {
            if (p.age > 30) {
                result.add(p);
            }
        }

        return result;
    }
    private static List<Person> filterByNameLengthGe2(List<Person> persons) {
        List<Person> result = new ArrayList<>();

        for (Person p : persons) {
            if (p.name.length() > 2) {
                result.add(p);
            }
        }

        return result;
    }
    private static List<Person> filterByLastNameLengthGe2(List<Person> persons) {
        List<Person> result = new ArrayList<>();

        for (Person p : persons) {
            if (p.lastName.length() > 2) {
                result.add(p);
            }
        }

        return result;
    }

}
