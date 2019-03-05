package ru.yusdm.javacore.lesson8setandcomparator.compareobjects.neithercomparatornocomparable;

import java.util.Set;
import java.util.TreeSet;

public class DemoNeitherComparableNoComparator {

    public static void main(String[] args) {
        Set<Person> persons = new TreeSet<>();
        persons.add(new Person("Ivan"));
    }

    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
    }
}
