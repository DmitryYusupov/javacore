package ru.yusdm.javacore.lesson3makeitoop.homework.quadraticequation;

public class StatelessQuadraticEquationResolver_1 {

    public void resolve(double coeffA, double coeffB, double coeffC) {
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


}