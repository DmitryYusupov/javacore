package ru.yusdm.javacore.lesson15concurrency.lesson.threads.part2;

import ru.yusdm.javacore.lesson3makeitoop.homework.quadraticequation.StatelessQuadraticEquationResolver_1;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 3/27/2019.
 */
public class C_QuadraticEquationResolution {
    private static class EquationResolver extends Thread {
        private StatelessQuadraticEquationResolver_1 resolver;
        private double a, b, c;

        public EquationResolver(StatelessQuadraticEquationResolver_1 resolver, double a, double b, double c) {
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
        StatelessQuadraticEquationResolver_1 resolver = new StatelessQuadraticEquationResolver_1();
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
