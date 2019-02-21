package ru.yusdm.javacore.lesson4oopinheritence.inheritence.withoutabstract;

public class Shape {
    protected float perimeter;
    protected float square;

    public void printPerimeter() {
        System.out.println("Perimeter is '" + perimeter + "'");
    }

    public void printSquare() {
        System.out.println("Square is '" + square + "'");
    }
}
