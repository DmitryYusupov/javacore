package ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo.impl;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.ArrayUtils;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo.ModelRepo;

import static ru.yusdm.javacore.lesson5oopinterface.autoservice.storage.Storage.models;


public class ModelMemoryRepo implements ModelRepo {

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
    public void delete(Model model) {
        Integer foundIndex = findModelIndexByEntity(model);

        if (foundIndex != null) {
            deleteModelByIndex(foundIndex);
        }
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
            System.out.println(model);
        }
    }

    private Integer findModelIndexByEntity(Model model) {
        for (int i = 0; i < models.length; i++) {
            if (models[i].equals(model)) {
                return i;
            }
        }

        return null;
    }

    private Integer findModelIndexById(Long modelId) {
        for (int i = 0; i < models.length; i++) {
            if (models[i].getId().equals(modelId)) {
                return i;
            }
        }
        return null;
    }

}
