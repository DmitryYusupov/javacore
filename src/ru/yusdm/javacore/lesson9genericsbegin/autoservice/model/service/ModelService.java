package ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.service;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelService extends BaseService<Model, Long> {

    List<Model> search(ModelSearchCondition searchCondition);

}
