package ru.yusdm.javacore.lesson15concurrency.lesson.threads.part2;

/**
 * Created by Admin on 3/27/2019.
 */
public class F_StopWorkerWithSync {
    private static boolean done = false;
    public static void main(String[] args) throws Exception{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i=1;
                while(!getDone()){i++;}
                System.out.println("Work has finished");
            }
        };
        new Thread(runnable).start();
        Thread.sleep(1000);
        System.out.println("I am stopping thread");
        setDone();
    }

    synchronized static public void setDone(){
        done = true;
    }

    public static synchronized boolean getDone(){
        return done;
    }
}
