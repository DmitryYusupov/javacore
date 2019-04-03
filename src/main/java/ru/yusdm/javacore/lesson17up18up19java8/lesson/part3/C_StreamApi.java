package ru.yusdm.javacore.lesson17up18up19java8.lesson.part3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Admin on 4/3/2019.
 */
public class C_StreamApi {

    /**
     * User story - tourism
     * <p>
     * 1;1 - start
     * 1;2 - mountains
     * <p>
     * [1-5; 2-10] - many bears
     * <p>
     * [3-10] - seat to boat
     * [4-10] - end trip
     */

    public static void main(String[] args) {
        //demoTrip();
        demoStreamInActions();
    }

    private static class Boat {
        private String name;

        public Boat(String name) {
            this.name = name;
        }
    }

    private static int cntr = 0;

    private static void demoTrip() {

        List<Boat> boats = Stream.of("1", "2", "3", "4", "5")
                .map(point -> {
                    // System.out.println("Cntr map" + (++cntr));
                    if (point.equals("1")) {
                        System.out.println("Start trip");
                    }
                    return point;
                })
                .filter(point -> {
                    //  System.out.println("Cntr filter" + (++cntr));
                    boolean skip = !new HashSet<>(Arrays.asList("3", "4")).contains(point);
                    return skip;
                }).map(point -> {
                    return new Boat(point);
                }).collect(toList());
    }

    private static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    private static void exceptionFunc() throws Exception{

    }

    private static boolean exceptionPredicateFunc(String s) throws Exception{
        return true;
    }
    private static void demoStreamInActions() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("ivan",2));
        persons.add(new Person("ivan",3));
        persons.add(new Person("ivan",4));
        persons.add(new Person("ivan",5));
        persons.add(new Person("ivan",6));

        List<String> personNamesWithLengthGThan5 = persons.stream()
                .map(p -> p.name)
                .filter(nameStr -> nameStr.length() > 5)
                .collect(toList());

        List<String> personNamesWithLengthGThan5With = persons.stream()
                .map(p -> p.name)
                .filter(p->{
                    try {
                        return exceptionPredicateFunc(p);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .filter(nameStr -> {
                    return nameStr.length() > 5;
                }
                    )
                .limit(3)
                .collect(toList());


        Map<String, List<Person>> collect = persons.stream()
                .filter(p -> p.name.length() > 5)
                .collect(Collectors.groupingBy(p -> p.name));

        Stream<Person> personStream = persons.stream()
                .filter(p -> p.name.length() > 5);

        Map<String, List<Person>> collect1 = personStream.collect(Collectors.groupingBy(p -> p.name));



        Optional<Integer> reduce = persons.stream()
                .map(person -> person.age)
                .reduce((i, i2) -> i + i2);

        Optional<Person> max = persons.stream()
                .max((p1, p2) -> p1.age - p2.age);

        Stream<Person> stream = persons.stream();
                stream.max(Comparator.comparingInt(p -> p.age));

                //stream.min(Comparator.comparingInt(p -> p.age));

        Optional<Person> max2 = persons.stream()
                .max(Comparator.comparingInt(p -> p.age));

        System.out.println();
    }


}
