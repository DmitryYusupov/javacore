package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.initializationorder;

public class Child extends Parent {

    private Integer k = method();

    static {
        System.err.println("Child static");
    }

    {
        System.err.println("Child init");
    }

    @Override
    protected Integer method() {
        return 1;
    }

    public Child() {
        System.err.println("Child constructor");
    }
}
