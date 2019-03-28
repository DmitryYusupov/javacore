package ru.yusdm.javacore.lesson3makeitoop.homework.quadraticequation;

public class OopQuadraticEquationResolver_4 {

    private double coeffA;
    private double coeffB;
    private double coeffC;

    public void resolve(double a, double b, double c) {

        assignInternalVars(a, b, c);
        Discriminant discriminant = new Discriminant();
        discriminant.calcDiscriminant();

        if (discriminant.geZero()) {
            double sqrtD = discriminant.getSqrtD();

            InnerSolutionFinder solutionFinder = new InnerSolutionFinder();
            double x1 = solutionFinder.calcX1(sqrtD);
            double x2 = solutionFinder.calcX2(sqrtD);

            solutionFinder.printResults(x1, x2);
        } else {
            printCantResolve(discriminant.getSqrtD());
        }
    }

    private void assignInternalVars(double a, double b, double c) {
        this.coeffA = a;
        this.coeffB = b;
        this.coeffC = c;
    }

    /**
     * Inner class to count discriminant
     */
    private class Discriminant {
        private double d;

        private void calcDiscriminant() {
            d = Math.pow(coeffB, 2) - 4 * coeffA * coeffC;
        }

        private double getSqrtD() {
            return Math.sqrt(d);
        }

        public double getD() {
            return d;
        }

        private boolean geZero() {
            return d > 0;
        }
    }


    private class InnerSolutionFinder {

        private double calcX1(double sqrtD) {
            return (-coeffB + sqrtD) / (2 * coeffA);
        }

        private double calcX2(double sqrtD) {
            return (-coeffB - sqrtD) / (2 * coeffA);
        }

        private void printResults(double x1, double x2) {
            System.out.println("x1 " + x1 + "; x2 " + x2);
        }
    }


    private void printCantResolve(double d) {
        System.out.format("Can't resolve. D is less than Zero '%s'", d);
    }

}
