package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part2;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 3/27/2019.
 */
public class A_JoinDemo {

    private static class Reader extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Thread has finished");
        }
    }

    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.start();
        try {
            //reader.join(100);
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End main");
    }

}
