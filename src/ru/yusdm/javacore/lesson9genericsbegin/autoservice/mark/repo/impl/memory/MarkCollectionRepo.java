package ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.repo.impl.memory;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.solutions.utils.StringUtils.isNotBlank;
import static ru.yusdm.javacore.lesson9genericsbegin.autoservice.storage.Storage.marksList;


public class MarkCollectionRepo implements MarkRepo {

    private MarkOrderingComponent orderingComponent = new MarkOrderingComponent();

    @Override
    public void add(Mark mark) {
        mark.setId(SequenceGenerator.getNextValue());
        marksList.add(mark);
    }

    @Override
    public Mark findById(long id) {
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
