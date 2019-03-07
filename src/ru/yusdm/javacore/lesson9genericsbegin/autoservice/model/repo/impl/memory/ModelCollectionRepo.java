package ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.repo.impl.memory;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.search.ModelSearchCondition;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.storage.SequenceGenerator;

import java.util.Collections;
import java.util.List;

import static ru.yusdm.javacore.lesson9genericsbegin.autoservice.storage.Storage.modelsList;


public class ModelCollectionRepo implements ModelRepo {

    @Override
    public void insert(Model model) {
        model.setId(SequenceGenerator.getNextValue());
        modelsList.add(model);
    }

    @Override
    public Model findById(Long id) {
        return findModelById(id);
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
    public void deleteById(Long id) {
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
