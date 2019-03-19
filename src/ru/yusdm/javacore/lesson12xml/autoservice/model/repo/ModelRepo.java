package ru.yusdm.javacore.lesson12xml.autoservice.model.repo;

import ru.yusdm.javacore.lesson12xml.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson12xml.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson12xml.autoservice.model.search.ModelSearchCondition;

import java.util.List;

public interface ModelRepo extends BaseRepo<Model, Long> {
    List<? extends Model> search(ModelSearchCondition searchCondition);
}
