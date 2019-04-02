package ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.dataclasses;

public class Pair {
    private String left;
    private String[] right;

    public Pair(String left, String[] right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String[] getRight() {
        return right;
    }
}
