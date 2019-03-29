package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Admin on 3/29/2019.
 */
public class B_MapConcurrency {

    private static class DataApplier extends Thread {

        private Map<Integer, String> map;
        private int from;
        private int to;
        private int length;

        public DataApplier(Map<Integer, String> map, int from, int to, int length) {
            this.map = map;
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public void run() {
            for (int i = from; i <= to; i++) {
                System.out.println(map.put(i, intToString(i, length)));
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

    private static class DataApplier2 extends Thread {
        private Map<Integer, String> map;
        private int from;
        private int to;
        private int length;

        public DataApplier2(Map<Integer, String> map, int from, int to, int length) {
            this.map = map;
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public void run() {
            for (int i = from; i <= to; i++) {
                applyData2(i);
            }
        }

        private void applyData(int i) {

            if (!map.containsKey(i)) {
                String put = map.put(i, i + "_data");
                if (put != null) {
                    System.out.println(put);
                }
            }

        }

        private void applyData2(int i) {
            map.computeIfAbsent(i, value -> value + "_data");
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
        shareResourceDemo2();
    }

    private static void shareResourceDemo() {

        //Map<Integer, String> storage = new HashMap<>(10);
        //Map<Integer, String> storage = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, String> storage = new ConcurrentHashMap<>();

        new DataApplier(storage, 1000, 5000, 5).start();
        new DataApplier(storage, 1001, 8000, 5).start();
//        new DataApplier(storage, 5001, 10000, 5).start();

        customSleep(1);
        System.out.println();

    }

    private static void shareResourceDemo2() {
        Map<Integer, String> storage = new ConcurrentHashMap<>();
        new DataApplier2(storage, 0, 5000, 5).start();
        new DataApplier2(storage, 0, 5000, 5).start();

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
