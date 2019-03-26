package ru.yusdm.javacore.lesson15concurrency.lesson.threads.common;

/**
 * Created by Admin on 3/25/2019.
 */
public final class ThreadUtils {

    private ThreadUtils(){

    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
