package ru.yusdm.javacore.lesson13serialization.override;

import java.io.File;

/**
 * Created by Admin on 3/22/2019.
 */
public class OverrideOverloadInheritenceDemo {

    private static class A {

        static void test() {
        }

        private void test2() {
        }

        protected void test3() {
        }


    }

    private static class B extends A {
        static void test() {
        }

        public void test2() {
        }

        public void test3() {
        }

        public void deleteFile(File f) {
        }

        public void deleteFile(String s) {
        }

        private Integer random() {
            return null;
        }


        /*private Double random(){
            return null;
        }
        */

    }

    public static void main(String[] args) {
        B b = null;
        b.test();
        b.test2();
    }

}
