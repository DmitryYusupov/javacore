package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part3;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 3/29/2019.
 */
public class A_ListConcurrency {


    private static class DataApplier extends Thread {
        private List<String> list;
        private int from;
        private int to;
        private int length;

        public DataApplier(List<String> list, int from, int to, int length) {
            this.list = list;
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public void run() {
            for (int i = from; i <= to; i++) {
                list.add(intToString(i, length));
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
        List<String> storage = new ArrayList<>(10);
        //List<String> storage = Collections.synchronizedList(new ArrayList<>());
      //  List<String> storage = new CopyOnWriteArrayList<>();
        new DataApplier(storage, 1, 500, 5).start();
        new DataApplier(storage, 501, 1000, 5).start();

        customSleep(1);

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println();
        Set<String> sorted = new TreeSet<>(storage);
        for (String s : sorted) {
            System.out.println(s);
        }

    }

    private static void customSleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
