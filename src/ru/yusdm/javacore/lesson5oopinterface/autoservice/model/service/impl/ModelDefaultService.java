package ru.yusdm.javacore.lesson5oopinterface.autoservice.model.service.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.service.ModelService;

public class ModelDefaultService implements ModelService {

    private final ModelRepo modelRepo;

    public ModelDefaultService(ModelRepo modelRepo) {
        this.modelRepo = modelRepo;
    }

    @Override
    public void add(Model model) {
        modelRepo.add(model);
    }

    @Override
    public Model findById(long id) {
        return modelRepo.findById(id);
    }

    @Override
    public void delete(Model model) {
        modelRepo.delete(model);
    }

    @Override
    public void deleteById(Long id) {
        modelRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        modelRepo.printAll();
    }

}
