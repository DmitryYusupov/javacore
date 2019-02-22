package ru.yusdm.javacore.lesson4oopinheritence.autoservice.model.repo;

import ru.yusdm.javacore.lesson4oopinheritence.autoservice.model.Model;

import static ru.yusdm.javacore.lesson4oopinheritence.autoservice.storage.Storage.models;

public class ModelMemoryRepo {

    private int modelIndex = -1;

    public void addModel(Model model) {
        if (modelIndex == models.length - 1) {
            Model[] newArrModels = new Model[models.length * 2];
            System.arraycopy(models, 0, newArrModels, 0, models.length);
            models = newArrModels;
        }

        modelIndex++;
        models[modelIndex] = model;
    }

    public Model findModelById(long id) {
        Integer modelIndex = findModelIndexById(id);
        if (modelIndex != null) {
            return models[modelIndex];
        }

        return null;
    }

    public void deleteModel(Model model) {
        Integer foundIndex = findModelIndexByEntity(model);

        if (foundIndex != null) {
            deleteModelByIndex(foundIndex);
        }
    }

    public void deleteModel(Long id) {
        Integer modelIndex = findModelIndexById(id);

        if (modelIndex != null) {
            deleteModelByIndex(modelIndex);
        }
    }

    private void deleteModelByIndex(int index) {
        Model[] newArrModels = new Model[models.length];
        System.arraycopy(models, 0, newArrModels, 0, index - 1);
        System.arraycopy(models, index, newArrModels, index - 1, models.length - index);
        models = newArrModels;
        modelIndex--;
    }

    public void printModels() {
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
