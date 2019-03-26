package ru.yusdm.javacore.lesson15concurrency.autoservice.mark.repo.impl.memory;

import ru.yusdm.javacore.lesson15concurrency.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson15concurrency.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson15concurrency.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson15concurrency.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson15concurrency.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson15concurrency.autoservice.storage.SequenceGenerator;
import ru.yusdm.javacore.lesson15concurrency.autoservice.storage.Storage;

import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson15concurrency.autoservice.storage.Storage.marksList;


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
        List<Mark> result = doSearch(searchCondition);

        boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
        if (needOrdering) {
            orderingComponent.applyOrdering(result, searchCondition);
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            result = getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<Mark> doSearch(MarkSearchCondition searchCondition) {
        List<Mark> result = new ArrayList<>();

        List<Mark> marksList = Storage.marksList;
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

    private List<Mark> getPageableData(List<Mark> marks, Paginator paginator) {
        return CollectionUtils.getPageableData(marks, paginator.getLimit(), paginator.getOffset());
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

    @Override
    public int countAll() {
        return marksList.size();
    }
}
