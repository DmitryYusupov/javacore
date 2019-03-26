package ru.yusdm.javacore.lesson6collectionlist.autoservice.model.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelService extends BaseService {

    void add(Model model);

    Model findById(Long id);

    void delete(Model model);

    List<Model> search(ModelSearchCondition searchCondition);

}
