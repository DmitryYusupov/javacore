package ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.repo;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkRepo extends BaseRepo<Mark, Long> {

    List<Mark> search(MarkSearchCondition searchCondition);

}
