package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.initializationorder;

public class Parent {
    private Integer k = method();
    static {
        System.err.println("Parent static");
    }

    {
        System.err.println("Parent init");
        k++;
    }

    protected Integer method() {
        System.err.println("Parent method");
        return 10;
    }

    public Parent() {
        System.err.println("Parent constructor");
    }
}
