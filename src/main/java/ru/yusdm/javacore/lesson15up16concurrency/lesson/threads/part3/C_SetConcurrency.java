package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part3;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 3/29/2019.
 */
public class C_SetConcurrency {

    private static class DataApplier extends Thread {
        private Set<String> set;
        private int from;
        private int to;
        private int length;

        private static final String s= "ss";
        public DataApplier(Set<String> set, int from, int to, int length) {
            this.set = set;
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public void run() {
            for (int i = from; i <= to; i++) {
                    set.add(intToString(i, length));
                //  System.out.println(Thread.currentThread().getName());
            }
        }

        private String intToString(int i, int length) {
            String data = i + "";

            if (data.length() < length) {
                StringBuilder builder = new StringBuilder();
                for (int k = 0; k < length - data.length(); k++) {
                    builder.append("0");
                }
                data = "data_" + builder.toString() + data;
            }

            return data;
        }
    }

    public static void main(String args[]) {
        //CompletableFuture completableFuture;

        // Set<String> storage = new HashSet<>(10);
        //Set<String> storage = Collections.synchronizedSet(new HashSet<>());
       // Set<String> storage = new CopyOnWriteArraySet<>();
        Set<String> storage = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

        new DataApplier(storage, -1000, 500, 5).start();
        new DataApplier(storage, 501, 1000, 5).start();

        customSleep(1);
        System.out.println();
    }

    private static void customSleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
