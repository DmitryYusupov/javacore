package ru.yusdm.javacore.lessonconcurrency.p1;

public class DeadlockDemo {


    private static class Task extends Thread {
        private final Integer num1;
        private final Integer num2;

        public Task(Integer num1, Integer num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        public void run() {
            synchronized (num1) {
                System.out.println(Thread.currentThread() + " Num1 " + num1);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (num2) {
                    System.out.println(Thread.currentThread() + " Num2 " + num2);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer num1 = 10;
        Integer num2 = 20;

        new Task(num1, num2).start();
        new Task(num2, num1).start();
    }

}
