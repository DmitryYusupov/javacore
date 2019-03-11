package ru.yusdm.javacore.lesson10.staticgeneric;

import java.util.List;

/**
 * Created by Admin on 3/11/2019.
 */
public class ArrayCreation<T> {

    T[] t;
    private static final int ARRAY_SIZE = 10;

    /**
     * Forbidden

     */
    //todo show in java 8
    public T[] createArray(){
        //new Person();
        //T t = new T();
     //   return new T[ARRAY_SIZE];
        return null;
    }

    private static class Person{
        private Person(String s){}
    }

    private static class Car{
        private Car(int power){}
    }


}
