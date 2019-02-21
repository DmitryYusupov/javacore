package ru.yusdm.javacore.lesson4oopinheritence.inheritence.withabstract;

public class InheritenceWithAbstractDemo {

    public static void main(String[] args) {
        //showNotPolymorphInit();
        showPolymorphInit();
    }

    private static void showNotPolymorphInit() {
        System.out.println("------Rect-----------");
        Rectangle rectangle = new Rectangle(3, 5);
        rectangle.calcSquare();
        rectangle.calcPerimeter();
        rectangle.printPerimeter();
        rectangle.printSquare();

        System.out.println("------Square-----------");
        Square square = new Square(3);
        square.calcPerimeter();
        square.calcSquare();
        square.printPerimeter();
        square.printSquare();
    }

    private static void showPolymorphInit() {
        System.out.println("------Rect-----------");
        Rectangle shape = new Rectangle(3, 5);
        shape.calcSquare();
        shape.calcPerimeter();
        shape.printPerimeter();
        shape.printSquare();

        System.out.println("------Square-----------");
        Square shape2 = new Square(3);
        shape2.calcPerimeter();
        shape2.calcSquare();
        shape2.printPerimeter();
        shape2.printSquare();
    }


}
