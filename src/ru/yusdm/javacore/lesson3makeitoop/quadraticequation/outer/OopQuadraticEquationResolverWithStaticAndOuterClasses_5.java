package ru.yusdm.javacore.lesson3makeitoop.quadraticequation.outer;

public class OopQuadraticEquationResolverWithStaticAndOuterClasses_5 {

    public EquationResult resolve(InputData input) {
        Discriminant discriminant = new Discriminant(input.getA(), input.getB(), input.getC());
        discriminant.calcDiscriminant();

        OuterSolutionFinder solutionFinder = new OuterSolutionFinder(input.getA(), input.getB(), discriminant.getD());

        EquationResult result = null;

        if (solutionFinder.canResolve()) {
            result = solutionFinder.resolveAndGet();
        }

        return result;
    }

    /**
     * Inner class to count discriminant
     */
    private static class Discriminant {
        private double d;
        private double a, b, c;

        private Discriminant(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        private void calcDiscriminant() {
            d = Math.pow(b, 2) - 4 * a * c;
        }

        public double getD() {
            return d;
        }
    }

}
