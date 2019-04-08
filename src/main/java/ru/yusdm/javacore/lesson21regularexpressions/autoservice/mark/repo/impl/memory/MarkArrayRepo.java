package ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.repo.impl.memory;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.utils.OptionalUtils;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static ru.yusdm.javacore.lesson21regularexpressions.autoservice.storage.Storage.marksArray;


public class MarkArrayRepo implements MarkRepo {
    private MarkOrderingComponent orderingComponent = new MarkOrderingComponent();
    private int markIndex = -1;

    @Override
    public Mark insert(Mark mark) {
        if (markIndex == marksArray.length - 1) {
            Mark[] newArrMarks = new Mark[marksArray.length * 2];
            System.arraycopy(marksArray, 0, newArrMarks, 0, marksArray.length);
            marksArray = newArrMarks;
        }

        markIndex++;
        mark.setId(SequenceGenerator.getNextValue());
        marksArray[markIndex] = mark;

        return mark;
    }

    @Override
    public void insert(Collection<Mark> marks) {
        marks.forEach(this::insert);
    }

    @Override
    public void update(Mark mark) {
        //we already in memory, no need to update object
    }

    @Override
    public Optional<Mark> findById(Long id) {
        return findMarkIndexById(id).map(markIndex -> marksArray[markIndex]);
    }

    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        List<Mark> result = doSearch(searchCondition);
        boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();

        if (needOrdering) {
            orderingComponent.applyOrdering(result, searchCondition);
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            return getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<Mark> doSearch(MarkSearchCondition searchCondition) {
        Mark[] result = new Mark[marksArray.length];
        int resultIndex = 0;

        for (Mark mark : marksArray) {
            if (mark != null) {
                boolean found = true;

                if (searchCondition.searchByCountry()) {
                    found = searchCondition.getCountry().equals(mark.getCountry());
                }

                if (found && searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(mark.getName());
                }

                if (found) {
                    result[resultIndex] = mark;
                    resultIndex++;
                }
            }
        }

        if (resultIndex > 0) {
            Mark[] toReturn = new Mark[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }

        return Collections.emptyList();
    }

    private List<Mark> getPageableData(List<Mark> marks, Paginator paginator) {
        return CollectionUtils.getPageableData(marks, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void deleteById(Long id) {
        findMarkIndexById(id).ifPresent(this::deleteMarkByIndex);
    }

    private void deleteMarkByIndex(int index) {
        ArrayUtils.removeElement(marksArray, index);
        markIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(marksArray).filter(Objects::nonNull).forEach(System.out::println);
    }

    private Optional<Integer> findMarkIndexById(long markId) {
        OptionalInt optionalInt = IntStream.range(0, marksArray.length).filter(i ->
                marksArray[i] != null && Long.valueOf(markId).equals(marksArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<Mark> findAll() {
        return new ArrayList<>(Arrays.asList(marksArray));
    }

    @Override
    public int countAll() {
        return marksArray.length;
    }
}
