package ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.service;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.repo.impl.MarkMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.repo.impl.MarkMemoryCollectionRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.repo.impl.ModelMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.repo.impl.ModelMemoryCollectionRepo;

public final class MarkServiceCreator {

    private MarkServiceCreator(){

    }

    public static MarkService getMarkService(StorageType storageType) {
        switch (storageType) {

            case MEMORY_ARRAY:
                return new MarkDefaultService(new MarkMemoryArrayRepo(), new ModelMemoryArrayRepo());

            case MEMORY_COLLECTION:{
                return new MarkDefaultService(new MarkMemoryCollectionRepo(), new ModelMemoryCollectionRepo());
            }

            default: {
                return null;
            }
        }
    }
}
