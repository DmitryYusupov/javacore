package ru.yusdm.javacore.lesson22relationaldb.autoservice.model.repo;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelRepo extends BaseRepo<Model, Long> {
    List<? extends Model> search(ModelSearchCondition searchCondition);
}
