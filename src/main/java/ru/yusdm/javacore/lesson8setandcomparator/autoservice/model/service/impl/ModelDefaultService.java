package ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.service.impl;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.search.ModelSearchCondition;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.service.ModelService;

import java.util.List;

public class ModelDefaultService implements ModelService {

    private final ModelRepo modelRepo;

    public ModelDefaultService(ModelRepo modelRepo) {
        this.modelRepo = modelRepo;
    }

    @Override
    public void add(Model model) {
        if (model != null) {
            modelRepo.add(model);
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
    public void deleteById(Long id) {
        if (id != null) {
            modelRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        modelRepo.printAll();
    }

    @Override
    public List<Model> search(ModelSearchCondition searchCondition) {
        return modelRepo.search(searchCondition);
    }

    @Override
    public void update(Model model) {
        if (model.getId() != null) {
            modelRepo.update(model);
        }
    }
}
