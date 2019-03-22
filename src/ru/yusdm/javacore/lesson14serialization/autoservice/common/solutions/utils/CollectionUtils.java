package ru.yusdm.javacore.lesson14serialization.autoservice.common.solutions.utils;

import java.util.List;

public final class CollectionUtils {

    public static<T>  T getLast(List<T> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(list.size() - 1);
        }
    }
}
