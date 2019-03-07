package ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.repo;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkRepo extends BaseRepo {

    void add(Mark mark);

    Mark findById(long id);

    List<Mark> search(MarkSearchCondition searchCondition);

    void update(Mark mark);

}
