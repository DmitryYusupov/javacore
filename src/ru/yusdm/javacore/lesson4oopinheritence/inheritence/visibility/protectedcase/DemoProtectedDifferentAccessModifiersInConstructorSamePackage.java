package ru.yusdm.javacore.lesson4oopinheritence.inheritence.visibility.protectedcase;

public class DemoProtectedDifferentAccessModifiersInConstructorSamePackage {

    public static void main(String[] args) {
        new Person();
        new Person("Dmitry", "Yusupov");
        System.out.println("Both constructors are");
    }

}
