package ru.yusdm.javacore.lesson15concurrency.threads;


public class A_ThreadWithClass {

    public static class ReadDataThread extends Thread {

        //todo show how to exit when endless loop

        /*@Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " Hello world");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("Interrupted ");
            }
        }*/

        private boolean stopMe = false;
        @Override
        public void run() {
            try {
                for (;;) {
                    System.out.println(Thread.currentThread().getName() + " Hello world");
                    if (isInterrupted()){
                        break;
                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Interrupted ");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " Hello world");
        Thread thread = new ReadDataThread();
        thread.start();
        System.out.println(thread.isInterrupted());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

}
