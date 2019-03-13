package ru.yusdm.javacore.lesson10genericsandexceptions.lesson.exceptions;

/**
 * Created by Admin on 3/13/2019.
 */
public class ExceptionsHandling {

    private void demoExceptionResolveWithTryCatch() {
        try {
            if (true) {
                throw new Exception("MyException");
            }
        } catch (Exception e) {
            System.out.println("Handle exception");
        }
    }

    private void demoExceptionResolveWithThrow() throws Exception {
        if (true) {
            throw new Exception("MyException");
        }
    }

    private void demoExceptionResolveWithReThrow() throws Exception {
        try {
            if (true) {
                throw new Exception("MyException");
            }
        } catch (Exception e) {
            System.out.println("Handle exception");
            throw e;
        }
    }

    private void demoExceptionResolveWithCreateNew() {
        try {
            if (true) {
                throw new Exception("MyException");
            }
        } catch (Exception e) {
            System.out.println("Handle exception");
            throw new RuntimeException("Everything is bad!");
        }
    }

    private void demoExceptionResolveWithCreateNew2() throws Exception {
        try {
            if (true) {
                throw new Exception("MyException");
            }
        } catch (Exception e) {
            System.out.println("Handle exception");
            Exception newE = new Exception("Everything is bad!");
            newE.initCause(e);

            throw newE;
        }
    }


    private void handleSeveralExceptions() {
        try {
            //input a,b,c - a = 0, then IllegalArgumentException
            //Division by zero - ArithmeticException

            //catch any other exception Exception
        } catch (IllegalArgumentException e) {
            System.out.println("Hey guy, input a koef pls");

        } catch (ArithmeticException e) {

        } catch (Exception e) {

        }
    }

    private void handleSeveralExceptions2() {
        try {
            //input a,b,c - a = 0, then IllegalArgumentException
            //Division by zero - ArithmeticException

            //catch any other exception Exception
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Problems while deal with equation");
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        ExceptionsHandling exceptionsHandling = new ExceptionsHandling();
        exceptionsHandling.handleSeveralExceptions();
    }
}
