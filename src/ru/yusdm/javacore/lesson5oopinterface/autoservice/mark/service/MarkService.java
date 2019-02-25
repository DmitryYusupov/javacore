package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.search.MarkSearchCondition;

public interface MarkService extends BaseService {

    void add(Mark mark);

    Mark findById(Long id);

    void delete(Mark mark);

    Mark[] search(MarkSearchCondition searchCondition);

}
