package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part1;

public class G_Singleton {

    private static G_Singleton instance;

    private G_Singleton() {

    }

    public static G_Singleton getInstance() {
        synchronized (G_Singleton.class) {
            if (instance == null) {
                instance = new G_Singleton();
            }
            return instance;
        }
    }

/*
    public synchronized static G_Singleton getInstance() {
        if (instance == null) {
            instance = new G_Singleton();
        }
        return instance;
    }
    */

}
