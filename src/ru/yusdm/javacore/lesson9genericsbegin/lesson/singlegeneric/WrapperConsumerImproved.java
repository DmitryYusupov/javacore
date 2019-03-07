package ru.yusdm.javacore.lesson9genericsbegin.lesson.singlegeneric;

/**
 * Created by Admin on 3/6/2019.
 */

public class WrapperConsumerImproved {

    public static class Box<T> {
        private T item;

        public Box(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }

    public void consumeWrapper(Box wrapper){
        System.out.println(wrapper.item.toString());
    }

    public static void main(String[] args) {
        Box<String> ivan = new Box<String>("Ivan");
        System.out.println(ivan.item.substring(2));

        Box<Integer> fakeIvan = new Box<Integer>(1111);
        System.out.println(++fakeIvan.item);
    }
}
