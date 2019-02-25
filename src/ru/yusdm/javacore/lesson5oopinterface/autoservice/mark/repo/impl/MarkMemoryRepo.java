package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.solutions.utils.StringUtils;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.search.MarkSearchCondition;

import static ru.yusdm.javacore.lesson5oopinterface.autoservice.common.solutions.utils.StringUtils.isNotBlank;
import static ru.yusdm.javacore.lesson5oopinterface.autoservice.storage.Storage.marks;


public class MarkMemoryRepo implements MarkRepo {

    private int markIndex = -1;

    @Override
    public void add(Mark mark) {
        if (markIndex == marks.length - 1) {
            Mark[] newArrMarks = new Mark[marks.length * 2];
            System.arraycopy(marks, 0, newArrMarks, 0, marks.length);
            marks = newArrMarks;
        }

        markIndex++;
        marks[markIndex] = mark;
    }

    @Override
    public Mark findById(long id) {
        Integer markIndex = findMarkIndexById(id);
        if (markIndex != null) {
            return marks[markIndex];
        }

        return null;
    }

    @Override
    public Mark[] search(MarkSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return new Mark[]{findById(searchCondition.getId())};
        } else {
            boolean searchByCountry =
                    isNotBlank(searchCondition.getCountry());

            boolean searchByName
                    = isNotBlank(searchCondition.getName());

            Mark[] result = new Mark[marks.length];
            int resultIndex = 0;

            for (Mark mark : marks) {
                if (mark != null) {
                    boolean found = true;

                    if (searchByCountry) {
                        found = searchCondition.getCountry().equals(mark.getCountry());
                    }

                    if (found && searchByName) {
                        found = searchCondition.getName().equals(mark.getName());
                    }

                    if (found && (searchByName || searchByCountry)) {
                        result[resultIndex] = mark;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                Mark toReturn[] = new Mark[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return toReturn;
            }
        }
        return new Mark[0];
    }

    @Override
    public void deleteById(long id) {
        Integer markIndex = findMarkIndexById(id);

        if (markIndex != null) {
            deleteMarkByIndex(markIndex);
        }
    }

    private void deleteMarkByIndex(int index) {
        ArrayUtils.removeElement(marks, index);
        markIndex--;
    }

    @Override
    public void printAll() {
        for (Mark mark : marks) {
            System.out.println(mark);
        }
    }

    private Integer findMarkIndexById(Long markId) {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i].getId().equals(markId)) {
                return i;
            }
        }
        return null;
    }

}
