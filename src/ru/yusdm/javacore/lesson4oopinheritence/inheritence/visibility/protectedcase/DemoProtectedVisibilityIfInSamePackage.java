package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.protectedcase;

public class DemoProtectedVisibilityIfInSamePackage {

    public static void main(String[] args) {
        Person person = new Person("Dmitry", "Yusupov");
        System.out.println("Person name: " + person.name);
        System.out.println("Person name: " + person.getLastName());

        System.out.println("Parson lastName: " + person.getLastName());
        //not allowed
        //System.out.println("Parson lastName: " + person.lastName);
    }

}
