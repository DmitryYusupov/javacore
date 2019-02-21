package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.protectedcase;

public class Person {
    protected String name;
    private String lastName;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    protected Person(){

    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }



}
