package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.withabstract;

public class Square extends AbstractShape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public void calcPerimeter() {
        perimeter = 4 * side;
    }

    public void calcSquare() {
        square = side * side;
    }
}
