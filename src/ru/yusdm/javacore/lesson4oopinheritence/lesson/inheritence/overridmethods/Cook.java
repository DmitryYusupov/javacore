package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.overridmethods;

/**
 * Created by Admin on 2/20/2019.
 */
public class Cook {
    private String dish;


    public Cook(String dish) {
        this.dish = dish;
    }

    public void cook(){
        System.out.println("Cook in action");
    }
}
