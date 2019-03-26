package ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.repo;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkRepo extends BaseRepo<Mark, Long> {

    List<Mark> search(MarkSearchCondition searchCondition);

}
