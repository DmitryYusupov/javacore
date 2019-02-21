package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.protectedcase.subpackage;


import ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.protectedcase.Person;

public class DemoProtectedDifferentAccessModifiersInConstructorOtherPackage {

    public static void main(String[] args) {
        //not accessable
        //new Person();
        new Person("Dmitry", "Ysupov");
    }

}
