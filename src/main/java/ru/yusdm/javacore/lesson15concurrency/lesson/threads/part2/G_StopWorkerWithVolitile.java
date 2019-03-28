package ru.yusdm.javacore.lesson15concurrency.lesson.threads.part2;

/**
 * Created by Admin on 3/27/2019.
 */
public class G_StopWorkerWithVolitile {

    private static volatile boolean done = false;
    private static int kkk = 9;

    public static void main(String[] args) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (!done) {
                    i++;
                }
                System.out.println("Work has finished");
            }
        };
        new Thread(runnable).start();
        Thread.sleep(1000);
        System.out.println("I am stopping thread");
        done = true;
    }

    public static void volatileExplain() {
        int x = 0;
        int y = 1;
        int z = 1;
        int w = 2;
        int k = 3;
        int result = 0;


        result = result + y;
        result = result + x;


        kkk = 10;
        done = true;

        result = result + y;
        result = result + z;

        /*
        result = result + y;
        result = result + x;
        result = result + y;
        result = result + z;
*/

        k = k + result;
    }


}
