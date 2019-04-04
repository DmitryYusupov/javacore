package ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.utils;

import java.util.Optional;
import java.util.OptionalInt;

public final class OptionalUtils {

    private OptionalUtils() {

    }

    public static Optional<Integer> valueOf(OptionalInt optionalInt) {
        return optionalInt.isPresent() ? Optional.of(optionalInt.getAsInt()) : Optional.empty();
    }
}
