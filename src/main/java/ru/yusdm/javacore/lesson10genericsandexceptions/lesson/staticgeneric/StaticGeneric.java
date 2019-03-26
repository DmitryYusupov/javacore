package ru.yusdm.javacore.lesson10genericsandexceptions.lesson.staticgeneric;

/**
 * Created by Admin on 3/11/2019.
 */

/*

Forbidden!!

public class StaticGeneric<T> {

    public static T getEntity() {
        T t = null;
        return t;
    }
}
*/

public class StaticGeneric<B> {

    public static<T> T getEntity() {
        T t = null;
        return t;
    }
}
