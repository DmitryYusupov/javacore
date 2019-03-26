package ru.yusdm.javacore.lesson2oopclasses.lesson.objectsmethods.tostring;

public class Person {

    private String name;
    private String lastName;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new Person("Dmitry", "Yusupov"));
    }
}
