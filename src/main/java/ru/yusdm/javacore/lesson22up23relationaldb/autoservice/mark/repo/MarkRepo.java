package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.mark.repo;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkRepo extends BaseRepo<Mark, Long> {

    List<Mark> search(MarkSearchCondition searchCondition);

    List<Mark> findAllMarksFetchingModels();

}
