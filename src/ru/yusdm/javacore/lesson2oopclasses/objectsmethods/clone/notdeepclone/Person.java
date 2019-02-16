package ru.yusdm.javacore.lesson2oopclasses.objectsmethods.clone.notdeepclone;

public class Person implements Cloneable {

    private String name;
    private String lastName;
    private Address address;

    public Person(String name, String lastName, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", \naddress=" + address +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Person origin = new Person("Dmitry", "Yusupov", new Address("Street", 11));
        Person clone = (Person) origin.clone();
        clone.address.setStreet("New Street");

        /**
         * We can see that street was changed both on origin and clone object.
         */
        System.out.println("----------origin--------------");
        System.out.println(origin);

        System.out.println("----------clone----------------");
        System.out.println(clone);
    }
}
