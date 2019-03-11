package ru.yusdm.javacore.lesson9genericsbegin.lesson.singlegenericmethod;

import java.io.Serializable;

/**
 * Created by Admin on 3/6/2019.
 */
public class SingleGenericMethod {

    public String test(){
        return "asd";
    }

    public <T extends Comparable & Serializable, Zu> int compare(T t, T other, Zu zu){
        return t.compareTo(other);
    }

    private static class Box<T>{
        T value;

        public Box(T value) {
            this.value = value;
        }

        public boolean isEquals(Box<?> otherT){
            return value.equals(otherT.value);
        }
    }


    private static class Box2<T extends Number>{
        T value;

        public Box2(T value) {
            this.value = value;
        }

        public boolean isEquals(Box2<?> otherT){
            return value.longValue() == otherT.value.longValue();
        }
    }


    public static void main(String[] args) {
        Box<Integer> box1 = new Box<>(2);
        Box<Long> box2 = new Box<>(2L);
        System.out.println(box1.isEquals(box2));
        System.out.println("------------------");

        Box2<Integer> box3 = new Box2<>(2);
        Box2<Long> box4 = new Box2<>(2L);
        System.out.println(box3.isEquals(box4));

    }

}
