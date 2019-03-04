package ru.yusdm.javacore.lesson8.sortstrings;

import java.util.Collections;
import java.util.List;

public class StringSorterWithCompareToMethod implements StringSorter {

    @Override
    public List<String> getSorted(List<String> toSort) {
        for (int i = 0; i < toSort.size() - 1; i++) {
            iterateAndSort(toSort);
        }
        return toSort;
    }

    private void iterateAndSort(List<String> toSort) {
        for (int i = 0; i < toSort.size() - 1; i++) {
            String prevStr = toSort.get(i);
            String nextStr = toSort.get(i + 1);

            if (nextStr.compareTo(prevStr) < 0) {
                Collections.swap(toSort, i, i + 1);
            }
        }
    }

}
