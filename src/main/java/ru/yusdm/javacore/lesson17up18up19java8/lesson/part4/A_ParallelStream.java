package ru.yusdm.javacore.lesson17up18up19java8.lesson.part4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Admin on 4/5/2019.
 */
public class A_ParallelStream {

    public static void main(String[] args) {
        //calcDuration(A_ParallelStream::demoSequentalStream);
        // calcDuration(A_ParallelStream::demoParallelStream);
       // calcDuration(A_ParallelStream::demoRunSeparateThreads);
       // calcDuration(A_ParallelStream::demoRunSeparateThreadsWithCustomPool);
        //System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static void demoRunSeparateThreadsWithCustomPool() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Runnable task1 = () -> demoParallelStream(6, "AAAAAAA", countDownLatch);
        new Thread(task1).start();
        sleepInSeconds(2);

        ForkJoinPool customPool = new ForkJoinPool();
        customPool.submit(()->{
            demoParallelStream(3, "BBBBBBB", countDownLatch);
        });


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 private static void demoRunSeparateThreads() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Runnable task1 = () -> demoParallelStream(6, "AAAAAAA", countDownLatch);
        new Thread(task1).start();
        sleepInSeconds(2);

        Runnable task2 = () -> demoParallelStream(3, "BBBBBBB", countDownLatch);
        new Thread(task2).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void demoParallelStream() {
        IntStream.range(1, 5)
                .parallel().mapToObj(i -> {
            longOperation(i);
            return i;
        }).collect(Collectors.toSet());
    }

    private static void demoParallelStream(int sleep, String tag, CountDownLatch countDownLatch) {
        IntStream.range(1, 10)
                .parallel().mapToObj(i -> {
            longOperation(sleep, tag, i);
            return i;
        }).collect(Collectors.toSet());

        countDownLatch.countDown();
    }

    private static void demoSequentalStream() {
        IntStream.range(1, 5).mapToObj(i -> {
            longOperation(i);
            return i;
        }).collect(Collectors.toSet());
    }

    private static void longOperation(int i) {
        String currentThread = Thread.currentThread().getName();
        System.out.println("'" + currentThread + "' Long operation for " + i + " in action");
        sleepInSeconds(2);
    }

    private static void longOperation(String tag, int i) {
        String currentThread = Thread.currentThread().getName();
        System.out.println("'" + currentThread + "' " + tag + " Long operation for " + i + " in action");
        sleepInSeconds(4);
    }

    private static void longOperation(int sleep, String tag, int i) {
        String currentThread = Thread.currentThread().getName();
        System.out.println("'" + currentThread + "' " + tag + " Long operation for " + i + " in action");
        sleepInSeconds(sleep);
    }

    private static void calcDuration(Runnable wrapper) {
        long buf = System.currentTimeMillis();
        wrapper.run();
        long result = System.currentTimeMillis() - buf;
        System.out.println("Duration " + result);
    }

    private static void sorta(){

    }

    private static  void demo(){
        calcDuration(()->sorta());
        calcDuration(()->sorta());
        calcDuration(()->sorta());
    }

    private static void test() {
        sleepInSeconds(2);
    }

    private static void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
