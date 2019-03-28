package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part2;

/**
 * Created by Admin on 3/27/2019.
 */
public class E_StopWorkerNoVolitileNoSync {

    private static boolean done = false;

    public static void main(String[] args) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (!done) {
                    i++;
                }
                System.out.println("Work has finished");
                /*
                if (!done){
                    while(true){
                        i++;
                    }
                }
                */
            }
        };
        new Thread(runnable).start();
        Thread.sleep(1000);
        System.out.println("I am stopping thread");
        done = true;
    }
}
