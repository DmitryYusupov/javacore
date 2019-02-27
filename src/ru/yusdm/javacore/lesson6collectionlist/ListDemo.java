package ru.yusdm.javacore.lesson6collectionlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 2/25/2019.
 */
public class ListDemo {

    public static void main(String[] args) {
        //iterateWithIterator();
        //iterateWithIteratorThrowingError();
        // iterateWithIteratorManual();
        // demoAddAll();
        // demoAddToInline();
        //demoAddToInlineList();
        //deleteElementFromListError();
        //deleteElementFromListWithIter();
      //  deleteElementFromListWithRemoveAll();

        //testContains();
        testContains2();
    }

    private static List<String> getTestCollection() {
        List<String> list = new ArrayList<>();
        list.add("Ivan");
        list.add("Petr");
        return list;
    }

    private static void iterateForEach() {
        List<String> list = getTestCollection();

        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void iterateForLoop() {
        List<String> list = getTestCollection();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private static void iterateWithIterator() {
        List<String> list = getTestCollection();

        Iterator<String> iter = list.iterator();

        while (iter.hasNext()) {
            String value = iter.next();
            System.out.println(value);
        }
    }

    private static void iterateWithIteratorManual() {
        List<String> list = getTestCollection();

        Iterator<String> iter = list.iterator();
        System.out.println(iter.next());
        System.out.println(iter.next());
    }

    private static void iterateWithIteratorThrowingError() {
        List<String> list = getTestCollection();

        Iterator<String> iter = list.iterator();

        while (iter.hasNext()) {
            String value = iter.next();
            System.out.println(value);
        }

        iter.next();
    }

    private static void demoAddAll() {
        List<String> names = new ArrayList<>();
        names.add("Ivan");
        names.add("Petr");

        List<String> other = new ArrayList<>(names);
        other.add("Dmitry");
        other.add("Darya");
        other.addAll(names);
        other.addAll(names);
        other.addAll(names);
        other.addAll(names);
        other.addAll(names);

        // names.addAll(other);
        // other.addAll(names);
        System.out.println(other);
    }

    private static void initInLine() {
        List<String> namesInLine = Arrays.asList("Ivan", "Petr");

        List<String> names = new ArrayList<>();
        names.add("Ivan");
        names.add("Petr");
    }

    public static void demoAddToInline() {
        List<String> namesInLine = Arrays.asList("Ivan", "Petr");
        namesInLine.add("Dmitry");
        System.out.println(namesInLine);
    }

    public static void demoAddToInlineList() {
        List<String> namesInLine = new ArrayList<>(Arrays.asList("Ivan", "Petr"));
        namesInLine.add("Dmitry");
        System.out.println(namesInLine);
    }

    public static void deleteElementFromListError() {
        List<String> namesInLine = new ArrayList<>(Arrays.asList("Ivan", "Petr"));
        namesInLine.add("Dmitry");
        namesInLine.add("Dmitry33");

        for (String s : namesInLine) {
            if (s.equals("Petr")) {
                namesInLine.remove(s);
            }
        }

        System.out.println(namesInLine);
    }

    public static void deleteElementFromListWithIter() {
        List<String> namesInLine = new ArrayList<>();
        namesInLine.add("A");
        namesInLine.add("B");
        namesInLine.add("C");
        namesInLine.add("D");
        namesInLine.add("1");
        namesInLine.add("D");
        namesInLine.add("D");
        namesInLine.add("D");
        namesInLine.add("D");

        Iterator<String> iterator = namesInLine.iterator();

        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("C".equals(value)) {
                iterator.remove();
            }
        }

        System.out.println(namesInLine);

    }

    public static void deleteElementFromListWithRemoveAll() {
        List<String> namesInLine = new ArrayList<>();
        namesInLine.add("A");
        namesInLine.add("B");
        namesInLine.add("C");
        namesInLine.add("D");
        namesInLine.add("D");
        namesInLine.add("D");
        namesInLine.add("D");
        namesInLine.add("D");

        List<String> toDelete = new ArrayList<>();
        for (String s : namesInLine) {
            if ("D".equals(s)) {
                toDelete.add(s);
            }
        }

        namesInLine.removeAll(toDelete);
        System.out.println(namesInLine);
    }

    private static class Person{
        private int id;
        private String name;
        private String lastName;

        public Person(int id, String name, String lastName) {
            this.id = id;
            this.name = name;
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (name != null ? !name.equals(person.name) : person.name != null) return false;
            return lastName != null ? lastName.equals(person.lastName) : person.lastName == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            return result;
        }
    }

    private static void testContains(){
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person(1, "Ivan","Ivanov");
        persons.add(person1);
        Person person2 = new Person(1, "Ivan","Ivanov");
        persons.add(person2);

        System.out.println(persons.contains(person1));
    }

    private static void testContains2(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Ivan","Ivanov"));
        persons.add(new Person(2, "Ivan","Ivanov"));

        System.out.println(
                persons.contains(new Person(1, "Ivan","Ivanov"))
        );
    }


}
