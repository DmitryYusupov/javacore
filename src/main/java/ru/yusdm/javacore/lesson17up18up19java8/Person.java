package ru.yusdm.javacore.lesson17up18up19java8;

import java.io.Serializable;

class Person implements Serializable, Cloneable {
    private int age;
    protected String name;
    public String lastName;

    public Person() {
    }

    public Person(int age, String name, String lastName) {
        this.age = age;
        this.name = name;
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static void staticMethod() {
        System.out.println("Static");
    }

    @Override
    public String toString() {
        return "Person{ " + super.toString() + " " +
                "age=" + age +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
