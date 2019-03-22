package ru.yusdm.javacore.lesson14serialization.lesson.overrideoverload;

/**
 * Created by Admin on 3/22/2019.
 */
public class OverrloadDemo {

    public static void main(String[] args) {
        OverrloadDemo testDemo = new OverrloadDemo();
        testDemo.test((short)1, (short)1);
    }


    /*  public void overrideoverload(int a, int b) {
          System.out.println();
      }*/
/*
    public void overrideoverload(Integer a, Integer b) {
        System.out.println();
    }
*/
    public void test(short b ,short a) {
        System.out.println();
    }

   /* public void overrideoverload(long a, long b) {
        System.out.println();
    }*/
/*
    public void overrideoverload(float a, float b) {
        System.out.println();
    }
*/
   /* public void overrideoverload(double a, double b) {
        System.out.println();
    }*/


    @Override
    public String toString() {
        return "OverrloadDemo{}";
    }

   /* public void overrideoverload(int... v) {
        System.out.println();
    }
*/

}
