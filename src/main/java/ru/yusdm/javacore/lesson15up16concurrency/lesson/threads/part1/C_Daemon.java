package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part1;

/**
 * Created by Admin on 3/25/2019.
 */
public class C_Daemon {

    private static class MyThread extends Thread {

        public MyThread() {
            setDaemon(true);
        }

        @Override
        public void run() {
            System.out.println("I am working");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I have just finished work");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
    }
}
