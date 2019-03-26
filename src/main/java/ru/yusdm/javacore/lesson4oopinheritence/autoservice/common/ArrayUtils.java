package ru.yusdm.javacore.lesson4oopinheritence.autoservice.common;

public final class ArrayUtils {

    private ArrayUtils(){

    }

    public static void removeElement(Object[] arr, int index) {
        System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
    }
}
