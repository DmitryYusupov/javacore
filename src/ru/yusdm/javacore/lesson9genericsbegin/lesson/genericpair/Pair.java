package ru.yusdm.javacore.lesson9genericsbegin.lesson.genericpair;

/**
 * Created by Admin on 3/6/2019.
 */
public class Pair<Left, Right> {
    private Left left;
    private Right right;
    public static String ssss = "dssdds";

    public Pair(Left left, Right right) {
        this.left = left;
        this.right = right;
    }

    public Left getLeft() {
        return left;
    }

    public Right getRight() {
        return right;
    }

    public static void main(String[] args) {
        Pair<Integer, String> monday = new Pair<>(1, "Monday");

        Pair<String, Integer> passport = new Pair<>("aaSS123",2123);
        String left = passport.getLeft();
        System.out.println(Pair.ssss);
    }
}
