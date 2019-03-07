package ru.yusdm.javacore.lesson2oopclasses.lesson.objectsmethods.clone.deepclone;

public class Person implements Cloneable {

    private String name;
    private String lastName;
    private Address address;

    public Person(String name, String lastName, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
    }

    /**
     * We hide this constructor, because of internal usage only
     *
     * @param src Source (origin) object to take data to copy
     */
    private Person(Person src) {
        copyActions(src, this);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Person(this);
    }

    private void copyActions(Person src, Person dest) {
        dest.name = src.name;
        dest.lastName = src.lastName;
        dest.address = new Address(src.address);
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
         * We can see that street was changed only on clone object, but origin object
         * still has the old value of street. It is because of deep copy.
         */
        System.out.println("----------origin--------------");
        System.out.println(origin);

        System.out.println("----------clone----------------");
        System.out.println(clone);
    }
}
