package ru.yusdm.javacore.lesson10genericsandexceptions.lesson.exceptions;

/**
 * Created by Admin on 3/13/2019.
 */
public class Test {

    public void demoTryCatchFinally() {
        try {
            //1 - open database connection
            //2- work with database
            if (true) {
                throw new Exception("Ooops");
            }
        } catch (Exception e) {
        } finally {
            //3 -close database
        }
    }

    public void demoTryFinally() throws Exception {
        try {
            //1 - open database connection
            //2- work with database
            if (true) {
                throw new Exception("Ooops");
            }
        } finally {
            //close database
        }
    }

    public static void demoFinalInAction() {
        try {
            System.out.println("Step 1");
            if (true) {
                throw new Exception("Any");
            }
            System.out.println("Step 2");
        } catch (Exception e) {
            System.out.println("Handle exception");
        } finally {
            System.out.println("Finally block");
        }
    }

    public static String demoFinalInAction2() {
        try {
            System.out.println("Step 1");
            if (true) {
                throw new Exception("Any");
            }

            return "Result 1";
        } catch (Exception e) {
            System.out.println("Here");
            return "Result 2";
        } finally {
            return "Result 3";
        }
    }


    public static void main(String[] args) {
        //demoFinalInAction();
        System.out.println(demoFinalInAction2());
    }
}
