package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;

public interface MarkService extends BaseService {

    void add(Mark mark);

    Mark findById(long id);

    void delete(Mark mark);

}
