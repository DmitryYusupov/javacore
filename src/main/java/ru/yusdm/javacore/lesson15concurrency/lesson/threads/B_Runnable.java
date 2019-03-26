package ru.yusdm.javacore.lesson15concurrency.lesson.threads;

/**
 * Created by Admin on 3/25/2019.
 */
public class B_Runnable {

    public static class ReadData implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Read data");
        }
    }

    public static class ReadDataThread implements Runnable {

        private Thread thread;

        public ReadDataThread() {
            thread = new Thread(this);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Read data");
        }

        public void interrupt() {
            thread.interrupt();
        }

        public void start() {
            thread.start();
        }

    }

    public static void main(String[] args) {

        Thread thread = new Thread(new ReadData());
        thread.start();
        thread.interrupt();

        ReadDataThread readDataThread = new ReadDataThread();
        readDataThread.start();
    }


}
