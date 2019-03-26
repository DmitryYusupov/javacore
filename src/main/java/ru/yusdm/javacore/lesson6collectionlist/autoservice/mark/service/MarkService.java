package ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.service;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.search.MarkSearchCondition;

import java.util.List;

public interface MarkService extends BaseService {

    void add(Mark mark);

    Mark findById(Long id);

    void delete(Mark mark);

    List<Mark> search(MarkSearchCondition searchCondition);

}
