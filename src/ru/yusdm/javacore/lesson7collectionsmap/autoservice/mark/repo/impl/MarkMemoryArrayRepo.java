package ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.repo.impl;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.solutions.utils.StringUtils.isNotBlank;
import static ru.yusdm.javacore.lesson7collectionsmap.autoservice.storage.Storage.marksArray;


public class MarkMemoryArrayRepo implements MarkRepo {
    private static final Mark[] EMPTY_MARKS_ARR = new Mark[0];
    private int markIndex = -1;

    @Override
    public void add(Mark mark) {
        if (markIndex == marksArray.length - 1) {
            Mark[] newArrMarks = new Mark[marksArray.length * 2];
            System.arraycopy(marksArray, 0, newArrMarks, 0, marksArray.length);
            marksArray = newArrMarks;
        }

        markIndex++;
        mark.setId(Storage.getNextValue());
        marksArray[markIndex] = mark;
    }

    @Override
    public Mark findById(long id) {
        Integer markIndex = findMarkIndexById(id);
        if (markIndex != null) {
            return marksArray[markIndex];
        }

        return null;
    }

    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            boolean searchByCountry = isNotBlank(searchCondition.getCountry());

            boolean searchByName = isNotBlank(searchCondition.getName());

            Mark[] result = new Mark[marksArray.length];
            int resultIndex = 0;

            for (Mark mark : marksArray) {
                if (mark != null) {
                    boolean found = true;

                    if (searchByCountry) {
                        found = searchCondition.getCountry().equals(mark.getCountry());
                    }

                    if (found && searchByName) {
                        found = searchCondition.getName().equals(mark.getName());
                    }

                    if (found) {
                        result[resultIndex] = mark;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                Mark toReturn[] = new Mark[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return new ArrayList<>(Arrays.asList(toReturn));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteById(long id) {
        Integer markIndex = findMarkIndexById(id);

        if (markIndex != null) {
            deleteMarkByIndex(markIndex);
        }
    }

    private void deleteMarkByIndex(int index) {
        ArrayUtils.removeElement(marksArray, index);
        markIndex--;
    }

    @Override
    public void printAll() {
        for (Mark mark : marksArray)
            if (mark != null) {
                System.out.println(mark);
            }
    }

    private Integer findMarkIndexById(long markId) {
        for (int i = 0; i < marksArray.length; i++) {
            if (marksArray[i] != null && Long.valueOf(markId).equals(marksArray[i].getId())) {
                return i;
            }
        }
        return null;
    }

}
