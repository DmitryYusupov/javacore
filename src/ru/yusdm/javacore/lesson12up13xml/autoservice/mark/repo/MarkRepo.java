package ru.yusdm.javacore.lesson12up13xml.autoservice.mark.repo;

import ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkRepo extends BaseRepo<Mark, Long> {

    List<Mark> search(MarkSearchCondition searchCondition);

}
