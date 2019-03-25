package ru.yusdm.javacore.lesson15concurrency.threads;

import ru.yusdm.javacore.lesson15concurrency.threads.common.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/25/2019.
 */
public class D_NoWaitNotify {

    private static class DataReader extends Thread {
        private List<String> data;

        public DataReader(List<String> data) {
            super("Reader");
            this.data = data;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String chunk = readChunkContent(i);
                System.out.println(Thread.currentThread().getName() + ". Read data " + chunk);
                data.add(chunk);
            }
        }

        private String readChunkContent(int i) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "data_" + i;
        }
    }

    private static class DataWriter extends Thread {
        private List<String> data;

        public DataWriter(List<String> data) {
            super("Writer");
            this.data = data;
        }

        @Override
        public void run() {
            prepareHardDrive();
            //ThreadUtils.sleep(3);

            if (data != null) {
                for (String s : data) {
                    System.out.println(Thread.currentThread().getName() + " .Write data " + s);
                }
            }
        }

        private void prepareHardDrive() {
            System.out.println("Prepare HD begin");
            ThreadUtils.sleep(1);
            System.out.println("Prepare HD end");
        }
    }

    public static void main(String[] args) {
        List<String> data = new ArrayList<>();

        DataReader dataReader = new DataReader(data);
        dataReader.start();

        ThreadUtils.sleep(10);

        DataWriter dataWriter = new DataWriter(data);
        dataWriter.start();
    }

}
