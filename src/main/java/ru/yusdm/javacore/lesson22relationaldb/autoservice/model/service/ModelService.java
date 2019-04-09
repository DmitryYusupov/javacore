package ru.yusdm.javacore.lesson22relationaldb.autoservice.model.service;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelService extends BaseService<Model, Long> {

    List<? extends Model> search(ModelSearchCondition searchCondition);

}
