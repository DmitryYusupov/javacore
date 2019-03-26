package ru.yusdm.javacore.lesson2oopclasses.lesson.objectsmethods.clone.deepclone;

public class Address {
    private String street;
    private int house;

    public Address(String street, int house) {
        this.street = street;
        this.house = house;
    }

    public Address(Address address) {
        copyActions(address, this);
    }

    private void copyActions(Address src, Address dest) {
        dest.house = src.house;
        dest.street = src.street;
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
