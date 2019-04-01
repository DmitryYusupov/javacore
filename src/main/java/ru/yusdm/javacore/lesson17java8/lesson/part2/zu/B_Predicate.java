package ru.yusdm.javacore.lesson17java8.lesson.part2.zu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Created by Admin on 4/1/2019.
 */
public class B_Predicate {
    private static class Person {
        private int age;
        private String name;
        private String lastName;
    }

    private static List<Person> filterPerson(List<Person> persons, BiPredicate<Person, String> match) {
        List<Person> result = new ArrayList<>();

        for (Person p : persons) {
            if (match.test(p, p.name)) {
                result.add(p);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        filterPerson(persons, (p, s) -> p.lastName.equals(s));
        filterPerson(persons, (p, s) -> p.name.equals(s));
    }


}
