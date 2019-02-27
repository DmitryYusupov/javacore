package ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.repo.impl;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.search.ModelSearchCondition;

import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson6collectionlist.autoservice.storage.Storage.modelsList;

public class ModelMemoryCollectionRepo implements ModelRepo {

    @Override
    public void add(Model model) {
        modelsList.add(model);
    }

    @Override
    public Model findById(long id) {
        return findModelById(id);
    }

    @Override
    public List<Model> search(ModelSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(long id) {
        Model found = findModelById(id);

        if (found != null) {
            modelsList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Model model : modelsList) {
            System.out.println(model);
        }
    }

    private Model findModelById(long modelId) {
        for (Model model : modelsList) {
            if (Long.valueOf(modelId).equals(model.getId())) {
                return model;
            }
        }
        return null;
    }

}
