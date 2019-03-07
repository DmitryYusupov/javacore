package ru.yusdm.javacore.lesson5oopinterface.lesson.dataclass;

/**
 * Created by Admin on 2/22/2019.
 */
public class Passport {
    private String serial;
    private String number;

    public Passport() {
    }

    public Passport(String serial, String number) {
        this.serial = serial;
        this.number = number;
    }

    public Passport(Passport passport) {
        this.number = passport.number;
        this.serial = passport.serial;
    }


    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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
