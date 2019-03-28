package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part2;

/**
 * Created by Admin on 3/27/2019.
 */
public class H_RaceConditionProblem {

    private static int counter = 0;

    private static class Incrimentor extends Thread {
        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Incrimentor().start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }

    /**
     * counter = 0
     *
     * 1 - Thread_1 0
     * 1 - Thread_2 0
     *
     * 2 - Thread_1 0+1 = 1
     * 2 - Thread_2 0+1 = 1
     *
     * 3 - Thread_1 write 1
     * 3 - Thread_2 write 1
     *
     * counter  = 1
     */
}
