package ru.yusdm.javacore.lesson4oopinheritence.lesson.inheritence.withoutabstract;

public class InheritenceWithoutAbstractDemo {

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

        Shape shape = new Rectangle(3, 5);
        ((Rectangle) shape).calcSquare();
        ((Rectangle) shape).calcPerimeter();
        shape.printPerimeter();
        shape.printSquare();

        System.out.println("------Square-----------");
        shape = new Square(3);
        ((Square) shape).calcPerimeter();
        ((Square) shape).calcSquare();
        shape.printPerimeter();
        shape.printSquare();
    }
}
