package ru.yusdm.javacore.lesson14serialization.autoservice.mark.repo;

import ru.yusdm.javacore.lesson14serialization.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson14serialization.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson14serialization.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkRepo extends BaseRepo<Mark, Long> {

    List<Mark> search(MarkSearchCondition searchCondition);

}
