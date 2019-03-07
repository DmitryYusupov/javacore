package ru.yusdm.javacore.lesson3makeitoop.homework.quadraticequation.outer;

public class OuterSolutionFinder {

    private double a;
    private double b;
    private double d;

    public OuterSolutionFinder(double a, double b, double d) {
        this.a = a;
        this.b = b;
        this.d = d;
    }

    private double calcX1() {
        return (-b + Math.sqrt(d)) / (2 * a);
    }

    private double calcX2() {
        return (-b - Math.sqrt(d)) / (2 * a);
    }

    public boolean canResolve() {
        return d >= 0;
    }

    public EquationResult resolveAndGet() {
        EquationResult solutions = new EquationResult();
        solutions.setX1(calcX1());
        solutions.setX2(calcX2());
        return solutions;
    }

}
