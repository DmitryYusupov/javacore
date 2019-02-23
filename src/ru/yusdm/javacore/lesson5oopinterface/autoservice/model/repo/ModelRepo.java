package ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;

public interface ModelRepo extends BaseRepo {

    void add(Model model);

    Model findById(long id);

}
