package ru.yusdm.javacore.lesson15up16concurrency.lesson.threads.part2;

import ru.yusdm.javacore.lesson3makeitoop.homework.quadraticequation.OopQuadraticEquationResolver_4;

import java.util.Arrays;
import java.util.List;

public class B_QuadraticEquationProblem {

    private static class EquationResolver extends Thread {
        private OopQuadraticEquationResolver_4 resolver;
        private double a, b, c;

        public EquationResolver(OopQuadraticEquationResolver_4 resolver, double a, double b, double c) {
            this.resolver = resolver;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public void run() {
            resolver.resolve(a, b, c);
        }
    }

    public static void main(String[] args) {
        OopQuadraticEquationResolver_4 resolver = new OopQuadraticEquationResolver_4();
        List<double[]> inputData = Arrays.asList(
                new double[]{2, 5, -3},
                new double[]{2, 5, -3},
                new double[]{1, -5, 6},
                new double[]{1, -5, 6}
        );

        for (double[] in : inputData) {
            new EquationResolver(resolver, in[0], in[1], in[2]).start();
        }
    }
}
