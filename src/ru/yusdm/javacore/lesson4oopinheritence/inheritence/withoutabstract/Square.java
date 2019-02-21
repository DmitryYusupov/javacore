package ru.yusdm.javacore.lesson4oopinheritence.inheritence.withoutabstract;

public class Square extends Shape{

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
