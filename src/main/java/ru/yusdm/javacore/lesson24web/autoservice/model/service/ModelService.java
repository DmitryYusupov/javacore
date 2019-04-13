package ru.yusdm.javacore.lesson24web.autoservice.model.service;

import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelService extends BaseService<Model, Long> {

    List<? extends Model> search(ModelSearchCondition searchCondition);
    List<Model> getModelsByMarkId(Long markId);
}
