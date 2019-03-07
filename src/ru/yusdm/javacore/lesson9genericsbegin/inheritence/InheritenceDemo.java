package ru.yusdm.javacore.lesson9genericsbegin.inheritence;

/**
 * Created by Admin on 3/6/2019.
 */
public class InheritenceDemo {
    public static class Box<T> {
        T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public static class ImprovedBox<String, Shelf_2> extends Box<String> {

        Shelf_2 value1;
        java.lang.String sss = "asd";

        public ImprovedBox(String s1, Shelf_2 s2) {
            this.value1 = s2;
            this.data = s1;
        }
    }

    public static void main(String[] args) {
        ImprovedBox<String, String> stringStringImprovedBox = new ImprovedBox<>("ImprovedBox", "Box");
        System.out.println(stringStringImprovedBox.getClass());

        ImprovedBox<String, Integer> stringStringImprovedBox2 = new ImprovedBox<>("ImprovedBox", 2);
        System.out.println(stringStringImprovedBox2.getClass());

        if (stringStringImprovedBox instanceof Box<?>) {
            System.out.println("Box is my Daddy!");
        }

        if (stringStringImprovedBox instanceof ImprovedBox<?, ?>) {
            System.out.println("Yes i am");
        }
    }

}
