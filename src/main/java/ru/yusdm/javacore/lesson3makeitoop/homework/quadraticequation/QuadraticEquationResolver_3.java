package ru.yusdm.javacore.lesson3makeitoop.homework.quadraticequation;

public class QuadraticEquationResolver_3 {

    private double coeffA;
    private double coeffB;
    private double coeffC;

    public void resolve(double a, double b, double c) {

        assignInternalVars(a, b, c);

        double d = calcDiscriminant();

        if (canResolve(d)) {
            double sqrtD = getSqrtD(d);
            double x1 = calcX1(sqrtD);
            double x2 = calcX2(sqrtD);
            printResults(x1, x2);
        } else {
            printCantResolve(d);
        }

    }

    private void assignInternalVars(double a, double b, double c) {
        this.coeffA = a;
        this.coeffB = b;
        this.coeffC = c;
    }

    private double calcDiscriminant() {
        return Math.pow(coeffB, 2) - 4 * coeffA * coeffC;
    }

    private boolean canResolve(double d) {
        return d > 0;
    }

    private double getSqrtD(double d) {
        return Math.sqrt(d);
    }

    private double calcX1(double sqrtD) {
        return (-coeffB + sqrtD) / (2 * coeffA);
    }

    private double calcX2(double sqrtD) {
        return (-coeffB - sqrtD) / (2 * coeffA);
    }

    private void printResults(double x1, double x2) {
        System.out.println("x1 " + x1 + "; x2 " + x2);
    }

    private void printCantResolve(double d) {
        System.out.format("Can't resolve. D is less than Zero '%s'", d);
    }
}
