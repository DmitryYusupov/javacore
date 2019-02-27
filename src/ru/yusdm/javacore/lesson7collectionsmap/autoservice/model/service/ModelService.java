package ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.service;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelService extends BaseService {

    void add(Model model);

    Model findById(Long id);

    void delete(Model model);

    List<Model> search(ModelSearchCondition searchCondition);

}
