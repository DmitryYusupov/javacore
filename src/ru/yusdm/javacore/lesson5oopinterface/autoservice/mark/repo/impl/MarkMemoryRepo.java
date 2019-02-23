package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo.MarkRepo;

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
    public void delete(Mark mark) {
        Integer foundIndex = findMarkIndexByEntity(mark);

        if (foundIndex != null) {
            deleteMarkByIndex(foundIndex);
        }
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

    private Integer findMarkIndexByEntity(Mark mark) {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i].equals(mark)) {
                return i;
            }
        }

        return null;
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
