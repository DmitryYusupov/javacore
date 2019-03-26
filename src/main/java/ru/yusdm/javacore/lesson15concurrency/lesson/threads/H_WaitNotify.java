package ru.yusdm.javacore.lesson15concurrency.lesson.threads;

import ru.yusdm.javacore.lesson15concurrency.lesson.threads.common.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/25/2019.
 */
public class H_WaitNotify {
    private static class DataReader extends Thread {
        private List<String> data;
        private Object sync;

        public DataReader(List<String> data, Object sync) {
            super("Reader");
            this.data = data;
            this.sync = sync;
        }

        @Override
        public void run() {
            synchronized (sync) {
                for (int i = 0; i < 10; i++) {
                    String chunk = readChunkContent(i);
                    System.out.println(Thread.currentThread().getName() + ". Read data " + chunk);
                    data.add(chunk);
                }

                sync.notifyAll();
               // ThreadUtils.sleep(5);
                System.out.println("Notify \n");
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
        private Object sync;

        public DataWriter(List<String> data, Object sync) {
            this.data = data;
            this.sync = sync;
        }

        @Override
        public void run() {


            synchronized (sync) {
                try {
                    prepareHardDrive();

                    System.out.println(Thread.currentThread().getName() +" Waiting for data");

                    sync.wait();

                    if (data != null) {
                        for (String s : data) {
                            System.out.println(Thread.currentThread().getName() + " .Write data " + s);
                        }
                    }

                    ThreadUtils.sleep(2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void prepareHardDrive() {
            System.out.println(Thread.currentThread().getName() + " Prepare HD begin");
            ThreadUtils.sleep(1);
            System.out.println(Thread.currentThread().getName() +" Prepare HD end");
        }
    }

    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        Object sync = "sync";

        for (int i = 0; i < 1; i++) {
            DataWriter dataWriter = new DataWriter(data, sync);
            dataWriter.start();
        }

        ThreadUtils.sleep(5);

        DataReader dataReader = new DataReader(data, sync);
        dataReader.start();

    }

}
