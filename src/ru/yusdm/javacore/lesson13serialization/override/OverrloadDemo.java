package ru.yusdm.javacore.lesson13serialization.override;

/**
 * Created by Admin on 3/22/2019.
 */
public class OverrloadDemo {

    public static void main(String[] args) {
        OverrloadDemo testDemo = new OverrloadDemo();
        testDemo.test((short)1, (short)1);
    }


    /*  public void override(int a, int b) {
          System.out.println();
      }*/
/*
    public void override(Integer a, Integer b) {
        System.out.println();
    }
*/
    public void test(short b ,short a) {
        System.out.println();
    }

   /* public void override(long a, long b) {
        System.out.println();
    }*/
/*
    public void override(float a, float b) {
        System.out.println();
    }
*/
   /* public void override(double a, double b) {
        System.out.println();
    }*/


    @Override
    public String toString() {
        return "OverrloadDemo{}";
    }

   /* public void override(int... v) {
        System.out.println();
    }
*/

}
