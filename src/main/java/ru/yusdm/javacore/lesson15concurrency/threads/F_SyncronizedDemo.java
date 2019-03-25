package ru.yusdm.javacore.lesson15concurrency.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/25/2019.
 */
public class F_SyncronizedDemo {

    public synchronized void wc() {
        System.out.println("Busy!");
    }

    public void spoon() {
        synchronized (this) {
            System.out.println("Busy!");
        }
    }

    /*
    public synchronized void spoon() {
        System.out.println("Busy!");
    }
    */



}
