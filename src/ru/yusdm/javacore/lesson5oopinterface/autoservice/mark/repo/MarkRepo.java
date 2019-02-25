package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.search.MarkSearchCondition;

public interface MarkRepo extends BaseRepo {

    void add(Mark mark);

    Mark findById(long id);

    Mark[] search(MarkSearchCondition searchCondition);

}
