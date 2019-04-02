package ru.yusdm.javacore.lesson17up18up19java8.lesson.part2.predefinedfuncinterfaces;

import java.util.function.Supplier;

/**
 * Created by Admin on 4/1/2019.
 */
public class D_Supplier {

    private static class Soldier {
        private String knife;
        private String gun;

        public Soldier(String knife, String gun) {
            System.out.println("Init ");
            this.knife = knife;
            this.gun = gun;
        }

        public Soldier() {
        }
    }

    public static void main(String[] args) {
        Soldier soldier = new Soldier("knife", "gun");

        war(() -> new Soldier("", ""));
        war(() -> new Soldier("a", ""));
        war(() -> new Soldier("", "f"));
        war(() -> new Soldier("f", ""));
        war(() -> new Soldier("", "f"));
    }

    private static void war(Supplier<Soldier> soldier) {
        Soldier soldier1 = null;

        if (false){
            soldier.get();
        }

       /* if (!soldier1.knife.isEmpty()) {
            System.out.println("I am fighting with knife");
        }

        if (!soldier1.gun.isEmpty()) {
            System.out.println("I am fighting with gun");
        }*/
    }

    private static void warWithKnife(Soldier soldier) {
        System.out.println("I am fighting with knife");
    }

    private static void warWithGun(Soldier soldier) {
        System.out.println("I am fighting with gun");
    }

}
