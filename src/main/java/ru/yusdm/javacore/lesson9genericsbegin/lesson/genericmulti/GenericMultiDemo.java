package ru.yusdm.javacore.lesson9genericsbegin.lesson.genericmulti;

/**
 * Created by Admin on 3/6/2019.
 */
public class GenericMultiDemo {
    public interface A{}
    public interface B{}

    public static class Common{}
    public static class AClass extends Common implements A{}
    public static class BClass extends Common implements A,B{}
    public static class CClass extends Common {}

    public static void main(String[] args) {
        method1(new AClass());
        method1(new BClass());
        method1(new CClass());

        method2(new AClass());
        method2(new BClass());
        //method2(new CClass());

        method3(new AClass());
        method3(new BClass());

        method4(new BClass());
    }
    private static void method1(Common common){}
    private static void method2(A a){}
    private static <T extends Common & A> void method3(T type){}
    private static <T extends Common & A, B> void method4(T type){}

}
