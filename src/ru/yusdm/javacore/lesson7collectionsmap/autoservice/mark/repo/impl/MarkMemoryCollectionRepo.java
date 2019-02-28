package ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.repo.impl;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.search.MarkSearchCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.solutions.utils.StringUtils.isNotBlank;
import static ru.yusdm.javacore.lesson7collectionsmap.autoservice.storage.Storage.marksList;


public class MarkMemoryCollectionRepo implements MarkRepo {
    @Override
    public void add(Mark mark) {
        marksList.add(mark);
    }

    @Override
    public Mark findById(long id) {
        return findMarkById(id);
    }

    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            boolean searchByCountry = isNotBlank(searchCondition.getCountry());

            boolean searchByName = isNotBlank(searchCondition.getName());

            List<Mark> result = new ArrayList<>();

            for (Mark mark : marksList) {
                if (mark != null) {
                    boolean found = true;

                    if (searchByCountry) {
                        found = searchCondition.getCountry().equals(mark.getCountry());
                    }

                    if (found && searchByName) {
                        found = searchCondition.getName().equals(mark.getName());
                    }

                    if (found) {
                        result.add(mark);
                    }
                }
            }

            return result;
        }
    }

    @Override
    public void deleteById(long id) {
        Mark found = findMarkById(id);

        if (found != null) {
            marksList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Mark mark : marksList) {
            System.out.println(mark);
        }
    }

    private Mark findMarkById(long markId) {
        for (Mark mark : marksList) {
            if (Long.valueOf(markId).equals(mark.getId())) {
                return mark;
            }
        }
        return null;
    }


}
