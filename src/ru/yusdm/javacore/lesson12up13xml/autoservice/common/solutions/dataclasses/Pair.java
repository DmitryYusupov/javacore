package ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.dataclasses;

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
