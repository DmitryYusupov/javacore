package ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.repo;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelRepo extends BaseRepo<Model, Long> {
    List<? extends Model> search(ModelSearchCondition searchCondition);
}
