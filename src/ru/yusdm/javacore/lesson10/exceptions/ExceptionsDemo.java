package ru.yusdm.javacore.lesson10.exceptions;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 3/11/2019.
 */
public class ExceptionsDemo {

    public static void main(String[] args) {
        // demoUncheckedException();
        // demoUncheckedExceptionWithTryCatch();
        // System.out.println("Result " + readData());
        /*
        for (int i = 0; i < 50; i++) {
            System.out.println(handleException());
        }*/

        for (int i = 0; i < 50; i++) {
            System.out.println(handleExceptionAllRuntime());
        }

        try {
            demoCheckedExceptionsThrowingException();
        } catch (FileNotFoundException e) {
            System.out.println("Dangerous potsyk!");
        }

        demoUnCheckedExceptionsThrowingException();
    }


    public static void demoUncheckedException() {
        List<? super Integer> list = new ArrayList<>();
        list.add(1);
        list.add(null);

        for (Object o : list) {
            System.out.println(o.toString());
        }

        System.out.println("End of function");
    }

    public static void demoUncheckedExceptionWithTryCatch() {
        try {
            List<? super Integer> list = new ArrayList<>();
            list.add(1);
            list.add(null);

            for (Object o : list) {
                System.out.println(o.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        System.out.println("End of function");
    }

    public static void demoCheckedExceptions() {
        try {
            InputStream inputStream =
                    new BufferedInputStream(
                            new FileInputStream(new File(""))
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void demoCheckedExceptionsThrowingException() throws
            FileNotFoundException {
        InputStream inputStream =
                new BufferedInputStream(
                        new FileInputStream(new File(""))
                );
    }

    public static void demoUnCheckedExceptionsThrowingException() throws
            RuntimeException {
        try {
            InputStream inputStream =
                    new BufferedInputStream(
                            new FileInputStream(new File(""))
                    );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (true) {
            //todo show example using autoservice
            throw new RuntimeException("");
        }
    }

    public static int readData() {
        List<Integer> data = null;
        try {
            return data.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int handleException() {
        List<Integer> data = null;
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                int i = 1 / 0;
            }
            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data.get(0);
        } catch (ArithmeticException e) {
            System.out.println("This is Division by zero");
            return -1;
        } catch (NullPointerException e) {
            System.out.println("This is NPE");
            return -1;
        }
    }

    public static int handleExceptionAllRuntime() {
        List<Integer> data = null;
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                int i = 1 / 0;
            }
            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data.get(0);
        } catch (ArithmeticException e) {
            System.out.println("This is Division by zero");
            return -1;
        } catch (NullPointerException e) {
            System.out.println("This is NPE");
            return -1;
        } catch (RuntimeException e) {
            System.out.println("This is runtime");
            return -1;
        } catch (Exception e) {
            System.out.println("This is exception");
            return -1;
        }
    }

    public static class Person {
        void test() throws Exception {}
    }

    public static class PersonChild extends Person {
        void test() throws RuntimeException
        {
            try {
                InputStream inputStream =
                        new BufferedInputStream(
                                new FileInputStream(new File(""))
                        );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PersonChild2 extends Person {
        void test() throws FileNotFoundException {

                InputStream inputStream =
                        new BufferedInputStream(
                                new FileInputStream(new File(""))
                        );
        }
    }

    public static class PersonChild3 extends Person {
        void test() throws Exception {

                InputStream inputStream =
                        new BufferedInputStream(
                                new FileInputStream(new File(""))
                        );
        }
    }

    public void demoPolymorphic(){
        List<Person> persons = Arrays.asList(
                new Person(),
                new PersonChild(),
                new PersonChild2(),
                new PersonChild3()
        );

        for (Person person: persons){
            try {
                person.test();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


