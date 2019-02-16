package ru.yusdm.javacore.lesson2oopclasses.createobjects.complexobject;

public class Person {
    private static final String UNDEFINED_STR = "";
    private static final int UNDEFINED_INT = -1;

    private String name;
    private String lastName;
    private int age;
    private Address address;
    private Passport passport;

    public Person(String name, String lastName, int age, Address address, Passport passport) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getStreet() {
        if (address != null) {
            return address.getStreet();
        } else {
            return UNDEFINED_STR;
        }
    }

    public int getHouseNumber() {
        if (address != null) {
            return address.getHouse();
        } else {
            return UNDEFINED_INT;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", \naddress=" + address +
                ", \npassport=" + passport +
                '}';
    }

    public static void main(String[] args) {
        Address address = new Address("Street", 20);
        Passport passport = new Passport("4109", "0000001");
        System.out.println(new Person("Dmitry", "Yusupov", 31, address, passport));
    }
}
