package ru.yusdm.javacore.lesson2oopclasses.createobjects;

public class Person {

    private String name;
    private String lastName;
    private int age;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    /**
     * Telescoping constructor
     *
     * @param name     Name
     * @param lastName Last name
     * @param age      age
     */
    public Person(String name, String lastName, int age) {
        this(name, lastName);
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new Person("Dmitry"));
        System.out.println(new Person("Dmitry", "Yusupov"));
        System.out.println(new Person("Dmitry", "Yusupov", 31));
    }

}
