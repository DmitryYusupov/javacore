package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.packageprivate;

public class Person {
    String name;
    String lastName;

    public Person() {
    }

    Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    void printPerson(){

    }
}
