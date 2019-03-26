package ru.yusdm.javacore.lesson10genericsandexceptions.lesson.mycollectionutils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/11/2019.
 */
public class MyCollectionUtils {

    public void copyLists(List<String> src, List<String> dest) {
        int i = 0;
        for (String s : src) {
            i++;
            if (i % 2 == 0) {
                dest.add(s);
            }
        }
    }

    private static class Person {
    }

    private static class VipPerson extends Person{
    }

    private static class VipPerson2 extends Person{
    }

    public void copyListsForPersons(List<Person> src, List<Person> dest) {
        for (Person person : src) {
            dest.add(person);
        }
    }

    public void copyListsForPersons2(List<? extends Person> src, List<Person> dest) {
        for (Person person : src) {
            dest.add(person);
        }
    }

    public void copyListsForPersons3(List<? extends Person> src, List<? super Person> dest) {
        for (Person person : src) {
            dest.add(person);
        }
    }

    public static void main(String args[]){
        MyCollectionUtils myCollectionUtils = new MyCollectionUtils();
        List<VipPerson> src = new ArrayList<>();
        List<Person> dest = new ArrayList<>();

        VipPerson vipPerson = null;
        dest.add(vipPerson);

        myCollectionUtils.copyListsForPersons2(src, dest);


        List<VipPerson> src2 = new ArrayList<>();
        List<Person> dest2 = new ArrayList<>();
        myCollectionUtils.copyListsForPersons3(src2, dest2);
    }
}
