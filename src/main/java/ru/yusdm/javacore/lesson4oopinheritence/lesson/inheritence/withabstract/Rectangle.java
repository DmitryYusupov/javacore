package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.withabstract;

public class Rectangle extends AbstractShape{

    private int a, b;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void calcPerimeter() {
        perimeter = 2 * a * b;
    }

    public void calcSquare() {
        square = a * b;
    }
}
