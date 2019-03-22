package ru.yusdm.javacore.lessonconcurrency.p1;

public class DemoSimpleThread {

    private static class SimpleThread extends Thread {

        public SimpleThread(boolean daemon) {
            setDaemon(daemon);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" I am running " + Thread.currentThread().getName());
        }
    }

    private static void demoSimpleClassThread() {
        new SimpleThread(false).start();
    }

    private static void demoSimpleClassThreadDaemon() {
        new SimpleThread(true).start();
    }

    private static void demoThreadAsRunnable() {
        new Thread(new ThreadBody()).start();
    }

    private static class ThreadBody implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        System.out.println("Main " + Thread.currentThread().getName());
        demoThreadAsRunnable();
    }
}
