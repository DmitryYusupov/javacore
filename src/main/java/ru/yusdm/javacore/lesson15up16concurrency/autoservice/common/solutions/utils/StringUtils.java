package ru.yusdm.javacore.lesson15up16concurrency.autoservice.common.solutions.utils;

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
