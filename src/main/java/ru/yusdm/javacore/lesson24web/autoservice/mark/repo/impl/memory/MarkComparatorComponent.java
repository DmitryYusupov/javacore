package ru.yusdm.javacore.lesson24web.autoservice.mark.repo.impl.memory;

import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.mark.search.MarkOrderByField;

import java.util.*;

import static ru.yusdm.javacore.lesson24web.autoservice.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static ru.yusdm.javacore.lesson24web.autoservice.mark.search.MarkOrderByField.COUNTRY;
import static ru.yusdm.javacore.lesson24web.autoservice.mark.search.MarkOrderByField.NAME;

public final class MarkComparatorComponent {

    private static final MarkComparatorComponent INSTANCE = new MarkComparatorComponent();
    private static Map<MarkOrderByField, Comparator<Mark>> comparatorsByField = new HashMap<>();
    /**
     * For complex comparator only
     */
    private static Set<MarkOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(COUNTRY, NAME));

    static {
        comparatorsByField.put(COUNTRY, getComparatorForCountryField());
        comparatorsByField.put(NAME, getComparatorForNameField());
    }

    private MarkComparatorComponent() {
    }


    public static MarkComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<Mark> getComparatorForNameField() {
        return new Comparator<Mark>() {
            @Override
            public int compare(Mark mark1, Mark mark2) {
                return getComparatorForNullableStrings().compare(mark1.getName(), mark2.getName());
            }
        };
    }

    private static Comparator<Mark> getComparatorForCountryField() {
        return new Comparator<Mark>() {
            @Override
            public int compare(Mark mark1, Mark mark2) {
                return getComparatorForNullableStrings().compare(mark1.getCountry(), mark2.getCountry());
            }
        };
    }

    public Comparator<Mark> getComparatorForField(MarkOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<Mark> getComplexComparator(MarkOrderByField field) {
        return new Comparator<Mark>() {

            @Override
            public int compare(Mark m1, Mark m2) {
                int result = 0;
                Comparator<Mark> markComparator = comparatorsByField.get(field);

                if (markComparator != null) {
                    result = markComparator.compare(m1, m2);
                    //if records have same order priority, i want to order them in their group
                    if (result == 0) {

                        //loop throug all possible sorting fields
                        for (MarkOrderByField otherField : fieldComparePriorityOrder) {
                            //if i haven't sorted by field which is taken from parameter in function, i do sorting
                            if (!otherField.equals(field)) {

                                result = comparatorsByField.get(otherField).compare(m1, m2);
                                //if sort result detected that records are not equals - we exit from loop,
                                //else continue
                                if (result != 0) {
                                    break;
                                }
                            }
                        }

                    }
                }


                return result;
            }
        };
    }
}
