package ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.service.impl;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.repo.ModelRepo;

import java.util.List;

public class MarkDefaultService implements MarkService {

    private final MarkRepo markRepo;
    private final ModelRepo modelRepo;

    public MarkDefaultService(MarkRepo markRepo, ModelRepo modelRepo) {
        this.markRepo = markRepo;
        this.modelRepo = modelRepo;
    }

    @Override
    public void add(Mark mark) {
        if (mark != null) {
            markRepo.add(mark);

            if (mark.getModels() != null) {
                for (Model model : mark.getModels()) {
                    if (model != null) {
                        modelRepo.add(model);
                    }
                }
            }
        }
    }

    @Override
    public Mark findById(Long id) {
        if (id != null) {
            return markRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Mark mark) {
        if (mark.getId() != null) {
            this.deleteById(mark.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            markRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        markRepo.printAll();
    }


    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        return markRepo.search(searchCondition);
    }

    @Override
    public void update(Mark mark) {
        if (mark.getId() != null) {
            markRepo.update(mark);
        }
    }
}
