package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;

public interface MarkRepo extends BaseRepo {
    void add(Mark mark);

    Mark findById(long id);

}
