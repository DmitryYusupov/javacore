package ru.yusdm.javacore.lesson3makeitoop.quadraticequation.outer;

public class OopQuadraticEquationResolverWithStaticAndOuterClassesMain {

    public static void main(String args[]) {

        OopQuadraticEquationResolverWithStaticAndOuterClasses_5 resolver = new OopQuadraticEquationResolverWithStaticAndOuterClasses_5();
        EquationResult result = resolver.resolve(new InputData(2, 5, -3));
        if (result != null) {
            result.printResults();
        } else {
            System.out.println("Can't resolve");
        }

    }
}
