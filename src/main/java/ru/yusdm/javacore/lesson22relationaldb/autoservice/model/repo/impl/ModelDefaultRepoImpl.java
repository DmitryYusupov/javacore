package ru.yusdm.javacore.lesson22relationaldb.autoservice.model.repo.impl;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.search.ModelSearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ModelDefaultRepoImpl implements ModelRepo {

    @Override
    public List<? extends Model> search(ModelSearchCondition searchCondition) {
        return null;
    }

    @Override
    public Model insert(Model entity) {
        return null;
    }

    @Override
    public void insert(Collection<Model> items) {

    }

    @Override
    public void update(Model entity) {

    }

    @Override
    public Optional<Model> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public List<Model> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
