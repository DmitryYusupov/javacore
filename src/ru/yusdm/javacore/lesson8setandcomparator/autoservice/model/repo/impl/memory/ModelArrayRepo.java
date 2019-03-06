package ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.repo.impl.memory;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.search.ModelSearchCondition;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.storage.SequenceGenerator;

import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson8setandcomparator.autoservice.storage.Storage.modelsArray;


public class ModelArrayRepo implements ModelRepo {
    private static final Model[] EMPTY_MODELS_ARR = new Model[0];
    private int modelIndex = -1;

    @Override
    public void add(Model model) {
        if (modelIndex == modelsArray.length - 1) {
            Model[] newArrModels = new Model[modelsArray.length * 2];
            System.arraycopy(modelsArray, 0, newArrModels, 0, modelsArray.length);
            modelsArray = newArrModels;
        }

        modelIndex++;
        model.setId(SequenceGenerator.getNextValue());
        modelsArray[modelIndex] = model;
    }

    @Override
    public Model findById(long id) {
        Integer modelIndex = findModelIndexById(id);
        if (modelIndex != null) {
            return modelsArray[modelIndex];
        }

        return null;
    }

    @Override
    public void update(Model model) {
        //we already in memory, no need to update object
    }

    @Override
    public List<Model> search(ModelSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(long id) {
        Integer modelIndex = findModelIndexById(id);

        if (modelIndex != null) {
            deleteModelByIndex(modelIndex);
        }
    }

    private void deleteModelByIndex(int index) {
        ArrayUtils.removeElement(modelsArray, index);
        modelIndex--;
    }

    @Override
    public void printAll() {
        for (Model model : modelsArray) {
            if (model != null) {
                System.out.println(model);
            }
        }
    }

    private Integer findModelIndexById(long modelId) {
        for (int i = 0; i < modelsArray.length; i++) {
            if (modelsArray[i] != null && Long.valueOf(modelId).equals(modelsArray[i].getId())) {
                return i;
            }
        }
        return null;
    }

}
