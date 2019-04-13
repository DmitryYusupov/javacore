package ru.yusdm.javacore.lesson24web.autoservice.model.repo;

import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelRepo extends BaseRepo<Model, Long> {
    List<? extends Model> search(ModelSearchCondition searchCondition);
    List<Model> getModelsByMarkId(long markId);

}
