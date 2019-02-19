package ru.yusdm.javacore.lesson3makeitoop.quadraticequation.outer;

public class EquationResult {
    private double x1;
    private double x2;

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void printResults() {
        System.out.println("x1 " + x1 + "; x2 " + x2);
    }
}
