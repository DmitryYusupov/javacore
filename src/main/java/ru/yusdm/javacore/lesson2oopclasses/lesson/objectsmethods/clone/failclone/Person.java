package ru.yusdm.javacore.lesson2oopclasses.lesson.objectsmethods.clone.failclone;

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

    public static void main(String[] args) throws CloneNotSupportedException {
        Person origin = new Person("Dmitry", "Yusupov");
        Person clone = (Person) origin.clone();
        System.out.println(clone);
    }
}
