package ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.service;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelService extends BaseService<Model, Long> {

    List<? extends Model> search(ModelSearchCondition searchCondition);

}
