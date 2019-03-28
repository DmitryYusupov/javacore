package ru.yusdm.javacore.lesson15concurrency.lesson.threads.part2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 3/27/2019.
 */
public class D_SyncDemo {
    private static class SmartWatch {
        //
        public synchronized String getTime() {
            printlnWithThreadName("Begin to calc time");
            customSleep(2);
            return new SimpleDateFormat("HH:mm").format(new Date());
        }

        public synchronized String getDate() {
            printlnWithThreadName("Begin to calc date");
            customSleep(2);
            return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        }

        public synchronized String getDateTime() {
            return getDate() + getTime() + getVersion();
        }

        public synchronized static String getVersion() {
            printlnWithThreadName("Begin to retuirn version");
            customSleep(2);
            return "v 1.0.0";
        }
    }

    private static class FirstSmartWatchConsumer extends Thread {
        private SmartWatch smartWatch;

        public FirstSmartWatchConsumer(SmartWatch smartWatch) {
            this.smartWatch = smartWatch;
        }

        @Override
        public void run() {
            smartWatch.getDateTime();
            smartWatch.getTime();
            smartWatch.getDate();
            smartWatch.getVersion();
        }
    }

    private static class SecondSmartWatchConsumer extends Thread {
        private SmartWatch smartWatch;

        public SecondSmartWatchConsumer(SmartWatch smartWatch) {
            this.smartWatch = smartWatch;
        }

        @Override
        public void run() {
            smartWatch.getDate();
            smartWatch.getVersion();
            smartWatch.getDate();
            smartWatch.getTime();
        }
    }

    public static void main(String[] args) {
        SmartWatch smartWatch = new SmartWatch();
        new FirstSmartWatchConsumer(smartWatch).start();
        customSleep(1);
        new SecondSmartWatchConsumer(smartWatch).start();

    }

    private static void printlnWithThreadName(String value) {
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
