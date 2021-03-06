package ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.repo;

import ru.yusdm.javacore.lesson15up16concurrency.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkRepo extends BaseRepo<Mark, Long> {

    List<Mark> search(MarkSearchCondition searchCondition);

}
