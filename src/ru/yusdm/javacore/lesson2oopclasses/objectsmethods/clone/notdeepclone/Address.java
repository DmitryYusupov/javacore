package ru.yusdm.javacore.lesson2oopclasses.objectsmethods.clone.notdeepclone;

public class Address {
    private String street;
    private int house;

    public Address(String street, int house) {
        this.street = street;
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    @Override
    public String toString() {
        String superStr = super.toString();
        return superStr + "\n" +
                "Address{" +
                "street='" + street + '\'' +
                ", house=" + house +
                '}';
    }
}
