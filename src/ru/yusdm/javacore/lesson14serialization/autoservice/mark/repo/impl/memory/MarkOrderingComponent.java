package ru.yusdm.javacore.lesson14serialization.autoservice.mark.repo.impl.memory;

import ru.yusdm.javacore.lesson14serialization.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson14serialization.autoservice.mark.search.MarkOrderByField;
import ru.yusdm.javacore.lesson14serialization.autoservice.mark.search.MarkSearchCondition;

import java.util.Comparator;
import java.util.List;

public class MarkOrderingComponent {


    public void applyOrdering(List<Mark> marks, MarkSearchCondition markSearchCondition) {
        Comparator<Mark> markComparator = null;

        MarkOrderByField field = markSearchCondition.getOrderByField();
        switch (markSearchCondition.getOrderType()) {

            case SIMPLE: {
                markComparator = MarkComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                markComparator = MarkComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (markComparator != null) {
            switch (markSearchCondition.getOrderDirection()) {

                case ASC:
                    marks.sort(markComparator);
                    break;
                case DESC:
                    marks.sort(markComparator.reversed());
                    break;
            }
        }
    }
}
