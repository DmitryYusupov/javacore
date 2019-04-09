package ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.impl;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.search.MarkSearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MarkDefaultRepoImpl implements MarkRepo {

    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        return null;
    }

    @Override
    public Mark insert(Mark entity) {
        return null;
    }

    @Override
    public void insert(Collection<Mark> items) {

    }

    @Override
    public void update(Mark entity) {

    }

    @Override
    public Optional<Mark> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public List<Mark> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }


}
