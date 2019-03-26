package ru.yusdm.javacore.lesson6collectionlist.autoservice.model.repo;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelRepo extends BaseRepo {

    void add(Model model);

    Model findById(long id);

    List<Model> search(ModelSearchCondition searchCondition);
}
