package ru.yusdm.javacore.lesson5oopinterface.dataclass;

/**
 * Created by Admin on 2/22/2019.
 */
public final class Person {
    private final String name;
    private final String lastName;
    private final Passport passport;

    public Person(String name, String lastName, Passport passport) {
        this.name = name;
        this.lastName = lastName;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Passport getPassport() {
        return new Passport(passport);
        //return passport;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport=" + passport +
                '}';
    }
}
