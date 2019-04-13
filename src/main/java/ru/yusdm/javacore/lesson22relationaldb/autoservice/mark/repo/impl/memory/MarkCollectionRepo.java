package ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.impl.memory;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.storage.SequenceGenerator;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.storage.Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static ru.yusdm.javacore.lesson22relationaldb.autoservice.storage.Storage.marksList;


public class MarkCollectionRepo implements MarkRepo {

    private MarkOrderingComponent orderingComponent = new MarkOrderingComponent();

    @Override
    public Mark insert(Mark mark) {
        mark.setId(SequenceGenerator.getNextValue());
        marksList.add(mark);

        return mark;
    }

    @Override
    public void insert(Collection<Mark> marks) {
        marks.forEach(this::insert);
    }

    @Override
    public Optional<Mark> findById(Long id) {
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

    @Override
    public List<Mark> findAllMarksFetchingModels() {
        return marksList;
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
        Optional<Mark> foundOptional = findMarkById(id);
        foundOptional.map(mark -> marksList.remove(mark));
    }

    @Override
    public void printAll() {
        marksList.forEach(System.out::println);
    }

    private Optional<Mark> findMarkById(long markId) {
        return marksList.stream().filter(mark -> Long.valueOf(markId).equals(mark.getId())).findAny();
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
