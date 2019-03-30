package ru.yusdm.javacore.lesson17java8.autoservice.common.solutions.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtils {

    private RandomUtils() {
    }

    public static int getRandomInt(int start, int end){
        return ThreadLocalRandom.current().nextInt(start, end + 1);
    }
}
