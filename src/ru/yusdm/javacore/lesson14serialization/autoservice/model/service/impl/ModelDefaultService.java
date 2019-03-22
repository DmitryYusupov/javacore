package ru.yusdm.javacore.lesson14serialization.autoservice.model.service.impl;

import ru.yusdm.javacore.lesson14serialization.autoservice.common.business.exception.AutoServiceUncheckedException;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.exception.unchecked.DeleteModelException;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.search.ModelSearchCondition;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson14serialization.autoservice.order.repo.OrderRepo;

import java.util.List;

import static ru.yusdm.javacore.lesson14serialization.autoservice.model.exception.ModelExceptionMeta.DELETE_MODEL_CONSTRAINT_ERROR;

public class ModelDefaultService implements ModelService {

    private final ModelRepo modelRepo;
    private final OrderRepo orderRepo;

    public ModelDefaultService(ModelRepo modelRepo, OrderRepo orderRepo) {
        this.modelRepo = modelRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void insert(Model model) {
        if (model != null) {
            modelRepo.insert(model);
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
        return modelRepo.search(searchCondition);
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
}
