package ru.yusdm.javacore.lesson17up18up19java8.lesson.part3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Admin on 4/3/2019.
 */
public class A_MethodReference {

    public static class PersonComparator {

        public int doWork233(A_MethodReference.Person p1, A_MethodReference.Person p2) {
            return p1.name.compareTo(p2.name);
        }

        private static int doWork2(A_MethodReference.Person p1, A_MethodReference.Person p2) {
            return p1.name.compareTo(p2.name);
        }
    }
    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Ivan"));
        persons.add(new Person("Dmitry"));
        persons.add(new Person("Petr"));

        PersonComparator personComparator = new PersonComparator();

        persons.sort(personComparator::doWork233);
        persons.sort(PersonComparator::doWork2);
        persons.sort((o1, o2) -> {
            PersonComparator personComparator1 = new PersonComparator();
            return personComparator1.doWork233(o1, o2);
        });

        persons.sort((p1, p2) -> {
            return p1.name.compareTo(p2.name);
        });

        persons.forEach(p -> {
            System.out.println(p);
        });
    }
}
