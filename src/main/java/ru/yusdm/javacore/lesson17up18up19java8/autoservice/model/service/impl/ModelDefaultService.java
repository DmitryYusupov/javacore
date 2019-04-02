package ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.service.impl;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.exception.unchecked.DeleteModelException;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.search.ModelSearchCondition;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.order.repo.OrderRepo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson17up18up19java8.autoservice.model.exception.ModelExceptionMeta.DELETE_MODEL_CONSTRAINT_ERROR;

public class ModelDefaultService implements ModelService {

    private final ModelRepo modelRepo;
    private final OrderRepo orderRepo;

    public ModelDefaultService(ModelRepo modelRepo, OrderRepo orderRepo) {
        this.modelRepo = modelRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public Model insert(Model model) {
        if (model != null) {
            modelRepo.insert(model);
        }

        return model;
    }

    @Override
    public void insert(Collection<Model> models) {
        if (models != null && !models.isEmpty()) {
            modelRepo.insert(models);
        }
    }

    @Override
    public Model findById(Long id) {
        if (id != null) {
            return modelRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Model model) {
        if (model.getId() != null) {
            this.deleteById(model.getId());
        }
    }

    @Override
    public void deleteById(Long id) throws AutoServiceUncheckedException {
        if (id != null) {

            boolean noOrders = orderRepo.countByModel(id) == 0;
            if (noOrders) {
                modelRepo.deleteById(id);
            } else {
                throw new DeleteModelException(DELETE_MODEL_CONSTRAINT_ERROR);
            }

        }
    }

    @Override
    public void printAll() {
        modelRepo.printAll();
    }

    @Override
    public List<? extends Model> search(ModelSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(modelRepo.findById(searchCondition.getId()));
        } else {
            return modelRepo.search(searchCondition);
        }
    }

    @Override
    public void update(Model model) {
        if (model.getId() != null) {
            modelRepo.update(model);
        }
    }

    @Override
    public List<Model> findAll() {
        return modelRepo.findAll();
    }

    @Override
    public int countAll() {
        return modelRepo.countAll();
    }
}
