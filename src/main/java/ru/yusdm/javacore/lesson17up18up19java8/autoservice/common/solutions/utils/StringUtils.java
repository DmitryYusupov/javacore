package ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.utils;

public final class StringUtils {

    private StringUtils() {

    }

    public static boolean isBlank(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}
