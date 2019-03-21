package ru.yusdm.javacore.lesson12up13xml.autoservice.mark.repo.impl.memory;

import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson12up13xml.autoservice.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson12up13xml.autoservice.storage.Storage.marksList;


public class MarkCollectionRepo implements MarkRepo {

    private MarkOrderingComponent orderingComponent = new MarkOrderingComponent();

    @Override
    public void insert(Mark mark) {
        mark.setId(SequenceGenerator.getNextValue());
        marksList.add(mark);
    }

    @Override
    public Mark findById(Long id) {
        return findMarkById(id);
    }

    @Override
    public void update(Mark mark) {
        //we already in memory, no need to update object
    }

    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<Mark> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }

            return result;
        }
    }

    private List<Mark> doSearch(MarkSearchCondition searchCondition) {
        List<Mark> result = new ArrayList<>();
        for (Mark mark : marksList) {
            if (mark != null) {
                boolean found = true;

                if (searchCondition.searchByCountry()) {
                    found = searchCondition.getCountry().equals(mark.getCountry());
                }

                if (found && searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(mark.getName());
                }

                if (found) {
                    result.add(mark);
                }
            }
        }
        return result;
    }


    @Override
    public void deleteById(Long id) {
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

    @Override
    public List<Mark> findAll() {
        return marksList;
    }
}
