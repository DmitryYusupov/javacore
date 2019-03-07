package ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.repo.impl.memory;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.search.MarkOrderByField;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.search.MarkSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarkOrderingComponent {


    class MyCompararable implements Comparable<String>{

        private String srcString;
        private boolean invert = false;

        public MyCompararable(String srcString) {
            this.srcString = srcString;
        }

        @Override
        public int compareTo(String o) {
            if (invert) {
                return (-1) * this.srcString.compareTo(o);
            }else{
                return  this.srcString.compareTo(o);
            }
        }
    }

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
                    Collections.sort(marks, markComparator);
                    break;
                case DESC:
                    Collections.sort(marks, markComparator.reversed());
                    break;
            }
        }
    }
}
