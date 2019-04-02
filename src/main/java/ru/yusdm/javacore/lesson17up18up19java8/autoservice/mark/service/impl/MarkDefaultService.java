package ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.service.impl;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.exception.unchecked.DeleteMarkException;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.search.MarkSearchCondition;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.order.repo.OrderRepo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson17up18up19java8.autoservice.mark.exception.MarkExceptionMeta.DELETE_MARK_CONSTRAINT_ERROR;

public class MarkDefaultService implements MarkService {

    private final MarkRepo markRepo;
    private final ModelService modelService;
    private final OrderRepo orderRepo;

    public MarkDefaultService(MarkRepo markRepo, ModelService modelService, OrderRepo orderRepo) {
        this.markRepo = markRepo;
        this.modelService = modelService;
        this.orderRepo = orderRepo;
    }

    @Override
    public Mark insert(Mark mark) {
        if (mark != null) {
            markRepo.insert(mark);

            if (mark.getModels() != null && !mark.getModels().isEmpty()) {

                for (Model model : mark.getModels()) {
                    model.setMarkId(mark.getId());
                    modelService.insert(model);
                }

            }
        }

        return mark;
    }

    @Override
    public void insert(Collection<Mark> marks) {
        if (marks != null && !marks.isEmpty()) {
            for (Mark mark : marks) {
                markRepo.insert(mark);

                if (mark.getModels() != null && !mark.getModels().isEmpty()) {
                    mark.getModels().replaceAll(model -> {
                        model.setMarkId(mark.getId());
                        return model;
                    });
                    modelService.insert(mark.getModels());
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
    public void deleteById(Long id) throws AutoServiceUncheckedException {
        if (id != null) {
            boolean noOrders = orderRepo.countByMark(id) == 0;

            if (noOrders) {
                removeAllModelsFromMark(id);
                markRepo.deleteById(id);
            } else {
                throw new DeleteMarkException(DELETE_MARK_CONSTRAINT_ERROR);
            }
        }
    }

    @Override
    public void printAll() {
        markRepo.printAll();
    }


    @Override
    public List<Mark> search(MarkSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(markRepo.findById(searchCondition.getId()));
        } else {
            return markRepo.search(searchCondition);
        }
    }

    @Override
    public void update(Mark mark) {
        if (mark.getId() != null) {
            markRepo.update(mark);
        }
    }

    @Override
    public void removeAllModelsFromMark(Long markId) throws AutoServiceUncheckedException {
        Mark mark = findById(markId);
        if (mark != null) {
            List<Model> models = mark.getModels() == null ? Collections.emptyList() : mark.getModels();

            for (Model model : models) {
                modelService.deleteById(model.getId());
            }

        }
    }

    @Override
    public List<Mark> findAll() {
        return markRepo.findAll();
    }

    @Override
    public int countAll() {
        return markRepo.countAll();
    }
}
