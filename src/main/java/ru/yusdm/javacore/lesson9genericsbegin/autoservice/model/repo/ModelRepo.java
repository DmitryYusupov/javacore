package ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.repo;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelRepo extends BaseRepo<Model, Long> {
    List<Model> search(ModelSearchCondition searchCondition);
}
