package ru.yusdm.javacore.lesson2oopclasses.initorder.fail;

public class Person {

    private Integer age = getIncrementedAge();

    public Person(Integer age) {
        this.age = age;
    }

    private int getIncrementedAge() {
        return age + 1;
    }

    public static void main(String[] args) {
        new Person(1111);
    }
}
