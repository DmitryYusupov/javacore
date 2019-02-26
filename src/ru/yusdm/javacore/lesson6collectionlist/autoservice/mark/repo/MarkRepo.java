package ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.repo;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkRepo extends BaseRepo {

    void add(Mark mark);

    Mark findById(long id);

    List<Mark> search(MarkSearchCondition searchCondition);

}
