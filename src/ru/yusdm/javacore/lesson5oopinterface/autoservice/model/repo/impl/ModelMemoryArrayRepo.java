package ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.search.ModelSearchCondition;

import static ru.yusdm.javacore.lesson5oopinterface.autoservice.storage.Storage.models;


public class ModelMemoryArrayRepo implements ModelRepo {
    private static final Model[] EMPTY_MODELS_ARR = new Model[0];
    private int modelIndex = -1;

    @Override
    public void add(Model model) {
        if (modelIndex == models.length - 1) {
            Model[] newArrModels = new Model[models.length * 2];
            System.arraycopy(models, 0, newArrModels, 0, models.length);
            models = newArrModels;
        }

        modelIndex++;
        models[modelIndex] = model;
    }

    @Override
    public Model findById(long id) {
        Integer modelIndex = findModelIndexById(id);
        if (modelIndex != null) {
            return models[modelIndex];
        }

        return null;
    }

    @Override
    public Model[] search(ModelSearchCondition searchCondition) {
        return EMPTY_MODELS_ARR;
    }

    @Override
    public void deleteById(long id) {
        Integer modelIndex = findModelIndexById(id);

        if (modelIndex != null) {
            deleteModelByIndex(modelIndex);
        }
    }

    private void deleteModelByIndex(int index) {
        ArrayUtils.removeElement(models, index);
        modelIndex--;
    }

    @Override
    public void printAll() {
        for (Model model : models) {
            if (model != null) {
                System.out.println(model);
            }
        }
    }

    private Integer findModelIndexById(long modelId) {
        for (int i = 0; i < models.length; i++) {
            if (models[i] != null && Long.valueOf(modelId).equals(models[i].getId())) {
                return i;
            }
        }
        return null;
    }

}
