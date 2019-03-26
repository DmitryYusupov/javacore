package ru.yusdm.javacore.lesson2oopclasses.lesson.createobjects.complexobject;

public class Passport {
    private String serial;
    private String number;

    public Passport(String serial, String number) {
        this.serial = serial;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "serial='" + serial + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
