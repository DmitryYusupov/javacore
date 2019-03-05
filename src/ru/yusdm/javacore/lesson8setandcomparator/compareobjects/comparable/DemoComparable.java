package ru.yusdm.javacore.lesson8setandcomparator.compareobjects.comparable;

import java.util.Set;
import java.util.TreeSet;

public class DemoComparable {

    public static void main(String[] args) {
        Set<Person> persons = new TreeSet<>();
        persons.add(new Person("Ivan", 800));
        persons.add(new Person("Petr", 300));
        persons.add(new Person("Semen", 600));

        for (Person person : persons) {
            System.out.println(person);
        }
    }

    private static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            if (other != null) {
                return Integer.compare(this.age, other.age);
            }
            return 1;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
