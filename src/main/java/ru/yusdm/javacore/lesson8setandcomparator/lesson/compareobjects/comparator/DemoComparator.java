package ru.yusdm.javacore.lesson8setandcomparator.lesson.compareobjects.comparator;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class DemoComparator {

    public static void main(String[] args) {

        Comparator<Person> personComparator = new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return Integer.compare(person1.age, person2.age);
            }
        };

        Set<Person> persons = new TreeSet<>(personComparator);
        persons.add(new Person("Ivan", 800));
        persons.add(new Person("Petr", 300));
        persons.add(new Person("Semen", 600));

        for (Person person : persons) {
            System.out.println(person);
        }
    }


    private static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
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
