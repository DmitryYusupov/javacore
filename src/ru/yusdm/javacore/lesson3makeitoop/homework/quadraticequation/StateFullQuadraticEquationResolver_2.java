package ru.yusdm.javacore.lesson3makeitoop.homework.quadraticequation;

public class StateFullQuadraticEquationResolver_2 {

    private double coeffA;
    private double coeffB;
    private double coeffC;

    public void resolve(double a, double b, double c) {

        assignInternalVars(a, b, c);

        double d = Math.pow(coeffB, 2) - 4 * coeffA * coeffC;

        if (d > 0) {
            double sqrtD = Math.sqrt(d);

            double x1 = (-coeffB + sqrtD) / (2 * coeffA);
            double x2 = (-coeffB - sqrtD) / (2 * coeffA);

            System.out.println("x1 " + x1 + "; x2 " + x2);
        } else {
            System.out.format("Can't resolve. D is less than Zero '%s'", d);
        }
    }

    private void assignInternalVars(double a, double b, double c) {
        this.coeffA = a;
        this.coeffB = b;
        this.coeffC = c;
    }

}
