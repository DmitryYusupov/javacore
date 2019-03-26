package ru.yusdm.javacore.lesson1basics.lesson;

public class OperatorsAndConstructs {

    public static void main(String args[]) {
        //demoIfElse();
        //demoForLoop();
        //demoDoWhileLoop();
        //  demoWhileLoop();

    //    demoSwitchNoBreak();
    //    demoSwitchWithBreak();
    }

    private static void demoIf() {
        if (true) {
            System.out.println("Condition is true, so hello world!");
        }
    }

    private static void demoIfElse() {
        long number = System.currentTimeMillis();
        boolean isEvenNumber = number % 2 == 0;
        if (isEvenNumber) {
            System.out.println("Number " + number + " is even");
        } else {
            System.out.println("Number " + number + " is NOT even");
        }
    }

    private static void demoForLoop() {
        int totalWaterInBucket = 0;

        for (int i = 0; i < 10; i++) {
            totalWaterInBucket = totalWaterInBucket + 1;
            System.out.println("Total water in bucket is " + totalWaterInBucket);
        }
    }

    private static void demoWhileLoop() {
        int totalWaterInBucket = 0;

        while (totalWaterInBucket < 10) {
            totalWaterInBucket = totalWaterInBucket + 1;
            System.out.println("Total water in bucket is " + totalWaterInBucket);
        }

        System.out.println("\nTotal water in bucket is " + totalWaterInBucket);
    }

    private static void demoDoWhileLoop() {
        int totalWaterInBucket = 0;
        do {
            totalWaterInBucket = totalWaterInBucket + 1;
            System.out.println("Total water in bucket is " + totalWaterInBucket);
        }
        while (totalWaterInBucket < 10);

        System.out.println("\nTotal water in bucket is " + totalWaterInBucket);
    }


    private static void demoSwitchNoBreak() {
        String name = "Ivan";

        switch (name) {

            case "Petr": {
                System.out.println("Hello, Petr!");
            }

            case "Dima": {
                System.out.println("Hello, Dima!");
            }

            case "Ivan": {
                System.out.println("Hello, Ivan");
            }

            case "Sam": {
                System.out.println("Hello, Sam");
            }

        }
    }

    private static void demoSwitchWithBreak() {
        String name = "Ivan";

        switch (name) {

            case "Petr": {
                System.out.println("Hello, Petr!");
                break;
            }

            case "Dima": {
                System.out.println("Hello, Dima!");
                break;
            }

            case "Ivan": {
                System.out.println("Hello, Ivan");
                break;
            }

            case "Sam": {
                System.out.println("Hello, Sam");
                break;
            }

        }
    }
}
