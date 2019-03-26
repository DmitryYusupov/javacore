package ru.yusdm.javacore.lesson2oopclasses.lesson.createobjects;

public class PersonWithUglyConstructor {

    private String name;
    private String lastName;
    private int age;

    private String passportSerial;
    private String pssportNumber;

    private String street;
    private int house;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", passportSerial='" + passportSerial + '\'' +
                ", pssportNumber='" + pssportNumber + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                '}';
    }

    public PersonWithUglyConstructor(String name, String lastName, int age, String passportSerial, String pssportNumber, String street, int house) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.passportSerial = passportSerial;
        this.pssportNumber = pssportNumber;
        this.street = street;
        this.house = house;
    }

    public static void main(String[] args) {
        System.out.println(
                new PersonWithUglyConstructor(
                        "Dmitry",
                        "Yusupov",
                        31,
                        "4122",
                        "000001",
                        "Street",
                        21
                ));
    }
}
