package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.visibility.protectedcase.subpackage;


import ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.visibility.protectedcase.Person;

public class DemoProtectedVisibilityIfInOtherPackage {

    public static void main(String[] args) {
        Person person = new Person("Dmitry", "Yusupov");
        //not allowed - caller is in other package
        //System.out.println("Person name: " + person.name);
        System.out.println("Person name: " + person.getName());

        System.out.println("Parson lastName: " + person.getLastName());
        //not allowed - it is private
        //System.out.println("Parson lastName: " + person.lastName);
    }

}
