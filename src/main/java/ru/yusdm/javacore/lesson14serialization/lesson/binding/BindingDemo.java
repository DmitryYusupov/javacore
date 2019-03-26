package ru.yusdm.javacore.lesson14serialization.lesson.binding;

/**
 * Created by Admin on 3/22/2019.
 */
public class BindingDemo {

    private static class Parent {
        private void test() {
            System.out.println("Parent test 1");
        }

        public void test2() {
            System.out.println("Parent test 2");
        }

        public static void test3() {
            System.out.println("Parent test 3");
        }
    }

    private static class Child extends Parent {
        private void test() {
            System.out.println("Child test 1");
        }

        public void test2() {
             System.out.println("Child test 2");
        }

        public static void test3() {
            System.out.println("Child test 3");
        }
    }

    public static void main(String[] args) {
        BindingDemoEx2 bindingDemoEx2 = new BindingDemoEx2();
        Parent p =  new Child();
        bindingDemoEx2.test(p);

        ff(new Child());
    }

    private static void ff(Parent p){
        BindingDemoEx2 bindingDemoEx2 = new BindingDemoEx2();
        bindingDemoEx2.test(p);

    }

    public void f(int a[]){

    }

    private static class BindingDemoEx2{
        public void test(Child child){
            System.out.println("Child");
            child.test3();
            Child.test3();
        }

        public void test(Parent parent){
            System.out.println("Parent");
            parent.test2();
        }
    }

    private static void example1(){
        Parent p = new Child();
        p.test();
        p.test2();
        p.test3();
    }


}
