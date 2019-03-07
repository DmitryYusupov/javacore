package ru.yusdm.javacore.lesson9genericsbegin.lesson.nottypesafe;

/**
 * Created by Admin on 3/6/2019.
 */
public class WrapperConsumer {

    public static class Box {
        private Object item;

        public Box(Object item) {
            this.item = item;
        }
    }

    public void consumeWrapper(Box wrapper){
        System.out.println(wrapper.item.toString());
    }

    public static void main(String[] args) {
        WrapperConsumer wrapperConsumer = new WrapperConsumer();
        wrapperConsumer.consumeWrapper(new Box("Ivan"));
        wrapperConsumer.consumeWrapper(new Box(1));
        wrapperConsumer.consumeWrapper(new Box(2L));

        Box ivan = new Box("Ivan");
        System.out.println(((String)ivan.item).substring(2));

        Box fakeIvan = new Box(1111);
        System.out.println(((String)fakeIvan.item).substring(2));
    }
}
