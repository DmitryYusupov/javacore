package ru.yusdm.javacore.lesson4oopinheritence.inheritence.withabstract;

public abstract class AbstractShape {

    protected float perimeter;

    protected float square;

    public abstract void calcPerimeter();

    public abstract void calcSquare();

    public void printPerimeter() {
        System.out.println("Perimeter is '" + perimeter + "'");
    }

    public void printSquare() {
        System.out.println("Square is '" + square + "'");
    }
}
