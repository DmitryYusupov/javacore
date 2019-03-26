package ru.yusdm.javacore.lesson4oopinheritence.autoservice.common;

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
