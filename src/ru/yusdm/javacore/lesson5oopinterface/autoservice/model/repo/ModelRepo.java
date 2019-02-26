package ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.search.ModelSearchCondition;

public interface ModelRepo extends BaseRepo {

    void add(Model model);

    Model findById(long id);

    Model[] search(ModelSearchCondition searchCondition);
}
