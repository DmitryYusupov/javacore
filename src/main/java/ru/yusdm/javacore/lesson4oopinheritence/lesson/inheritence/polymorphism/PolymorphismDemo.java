package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.polymorphism;


import ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.withabstract.AbstractShape;
import ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.withabstract.Rectangle;
import ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.withabstract.Square;

public class PolymorphismDemo {

    public static void main(String[] args) {
        polymorphismInAction();
    }

    private static void polymorphismInAction() {
        AbstractShape[] shapes = new AbstractShape[2];
        shapes[0] = new Rectangle(3, 4);
        shapes[1] = new Square(3);
        for (AbstractShape shape : shapes) {
            calculateDataAndPrint(shape);
        }
    }
    private static void calculateDataAndPrint(AbstractShape shape) {
        shape.calcPerimeter();
        shape.calcSquare();
        shape.printPerimeter();
        shape.printSquare();
    }
}
