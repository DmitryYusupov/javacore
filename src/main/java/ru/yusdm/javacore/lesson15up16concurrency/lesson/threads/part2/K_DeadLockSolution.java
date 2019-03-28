package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part2;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 3/27/2019.
 */
public class K_DeadLockSolution {
    private static class Account {
        private Long id;
        private int value;

        public Account(Long id, int value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "id=" + id +
                    ", value=" + value +
                    '}';
        }
    }


    private static Thread transfer(Account src, Account dest, int money) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Map<Integer, Account> hashCodeAccountMap = new HashMap<>();
                hashCodeAccountMap.put(src.hashCode(), src);
                hashCodeAccountMap.put(dest.hashCode(), dest);

                Set<Integer> hashCodeSet = new TreeSet<>(hashCodeAccountMap.keySet());
                Iterator<Integer> iter = hashCodeSet.iterator();

                int firstHash = iter.next();
                int secondHash = iter.next();

                synchronized (hashCodeAccountMap.get(firstHash)) {

                    printWithThreadName("Get money form account " + src.id);
                    customSleep(1);

                    synchronized (hashCodeAccountMap.get(secondHash)) {
                        customSleep(1);
                        src.value = src.value - money;

                        printWithThreadName("Transfer money to account " + dest.id);
                        customSleep(3);
                        dest.value = dest.value + money;
                        printWithThreadName("Transfer successfully finished");
                    }
                }
            }

        });

        thread.start();
        return thread;
    }

    public static void main(String[] args) {
        Class clazz = Runnable.class;
        clazz.getMethods();

        Account src = new Account(1L, 200);
        Account dest = new Account(2L, 900);

        Thread transfer1 = transfer(src, dest, 100);
        Thread transfer2 = transfer(dest, src, 100);

        try {
            transfer1.join();
            transfer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(src);
        System.out.println(dest);

       /* boolean f = true;
        if (f){
            System.out.println("Hello");
        }*/

        System.out.println("Hello");

    }

    private static void printWithThreadName(String value) {
        System.out.println(Thread.currentThread().getName() + " " + value);
    }

    private static void customSleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
