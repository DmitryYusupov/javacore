package ru.yusdm.javacore.lesson17up18up19java8.lesson.part3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 4/3/2019.
 */
public class B_Optional {

    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
    }


    public static Person findByName(String name) {
        return null;
    }


    public static Optional<Person> findByNameNpeSafe(String name) {
        return Optional.of(new Person("Semen"));
    }

    public static void main(String[] args) {
        //  demoRescue();

        /*Optional<Person> optionalPerson = findByNameNpeSafe("ivan");
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            System.out.println(person.name);
        }*/



       /* int k = strToInt("1");
        Integer k2 = strToInt("Ivan");

        Optional<Integer> integer = strToIntBestPractice(null);
        if (integer.isPresent()) {

        }*/

        //reportUserMonthIncomePresent();
        //reportUserMonthIncomeAbsent();

        //reportUserMonthIncomeAbsentRescue();

        long cur = System.currentTimeMillis();
        reportUserMonthIncomeAbsentImprove(true);
        reportUserMonthIncomeAbsentImprove(false);
        System.out.println(System.currentTimeMillis() - cur);
        cur = System.currentTimeMillis();
        System.out.println("----------------");
        reportUserMonthIncomeAbsentImprove2(true);
        reportUserMonthIncomeAbsentImprove2(false);
        System.out.println(System.currentTimeMillis() - cur);
    }

    private static void demoProblem() {
        Person person = findByName("Ivan");
        System.out.println(person.name);
    }

    private static void demoRescue() {
        Person person = findByName("Ivan");

        if (person != null) {
            System.out.println(person.name);
        }
    }


    private static Optional<Person> findSmartest(String name) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Ivan"));
        persons.add(new Person("Semen"));

        Person found = null;
        for (Person person : persons) {
            if (person.name.equals(name)) {
                found = person;
                break;
            }
        }

        return Optional.ofNullable(found);
    }

    private static Optional<Person> findSmartest2(String name) {

        if (name.length() > 5) {
            List<Person> persons = new ArrayList<>();
            persons.add(new Person("Ivan"));
            persons.add(new Person("Semen"));
            Person found = null;
            for (Person person : persons) {
                if (person.name.equals(name)) {
                    found = person;
                    break;
                }
            }
            return Optional.ofNullable(found);
        } else if (name.length() > 0) {
            return Optional.of(new Person("Gena"));
        } else {
            return Optional.empty();
        }
    }

    public static Integer strToInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static Optional<Integer> strToIntBestPractice(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static void reportUserMonthIncomePresent() {
        Optional<Integer> incomeWithMoney = getMoney(true);
        if (incomeWithMoney.isPresent()) {
            System.out.println("Ivan " + incomeWithMoney.get());
        }
    }


    private static void reportUserMonthIncomeAbsent() {
        Optional<Integer> incomeWithMoney = getMoney(false);
        if (incomeWithMoney.isPresent()) {
            System.out.println("Ivan " + incomeWithMoney.get());
        }
    }

    private static void reportUserMonthIncomeAbsentRescue() {
        Optional<Integer> incomeWithMoney = getMoney(false);
        if (incomeWithMoney.isPresent()) {
            System.out.println("Ivan " + incomeWithMoney.get());
        } else {
            System.out.println("Ivan " + 0);
        }
    }

    private static void reportUserMonthIncomeAbsentImprove(boolean hasMoney) {
        Optional<Integer> incomeWithMoney = getMoney(hasMoney);
        Integer income = incomeWithMoney.orElse(complexLogic());
        System.out.println("Ivan " + income);
    }

    private static int complexLogic() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Complex logic");
        return 0;
    }

    private static void reportUserMonthIncomeAbsentImprove2(boolean hasMoney) {
        Optional<Integer> incomeWithMoney = getMoney(hasMoney);
        Integer income = incomeWithMoney.orElseGet(() -> complexLogic());
        System.out.println("Ivan " + income);
    }

    private static Optional<Integer> getMoney(boolean hasMoney) {
        if (hasMoney) {
            return Optional.of(3333333);
        } else {
            return Optional.empty();
        }
    }

}
