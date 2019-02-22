package ru.yusdm.javacore.lesson5oopinterface.enumtest.complexenum;

/**
 * Created by Admin on 2/22/2019.
 */
public class Person implements TellAboutYourSelf {
    @Override
    public void meIs() {
        System.out.println("I am crazy!");
    }
}
