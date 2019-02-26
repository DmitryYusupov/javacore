package ru.yusdm.javacore.lesson6collectionlist.autoservice.model.service;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.repo.impl.ModelMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.repo.impl.ModelMemoryCollectionRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.service.impl.ModelDefaultService;

public final class ModelServiceCreator {

    private ModelServiceCreator(){

    }

    public static ModelService getModelService(StorageType storageType) {
        switch (storageType) {

            case MEMORY_ARRAY:
                return new ModelDefaultService(new ModelMemoryArrayRepo());

            case MEMORY_COLLECTION:{
                return new ModelDefaultService(new ModelMemoryCollectionRepo());
            }

            default: {
                return null;
            }
        }
    }
}
