package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.storage;

public final class SequenceGenerator {
    private static long value = 0;

    private SequenceGenerator() {

    }

    public static long getNextValue() {
        return ++value;
    }
}
