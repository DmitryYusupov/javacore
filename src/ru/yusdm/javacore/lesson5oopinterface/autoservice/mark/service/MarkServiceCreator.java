package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo.impl.MarkMemoryArrayRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo.impl.ModelMemoryArrayRepo;

public final class MarkServiceCreator {

    private MarkServiceCreator(){

    }

    public static MarkService getMarkService(StorageType storageType) {
        switch (storageType) {

            case MEMORY_ARRAY:
                return new MarkDefaultService(new MarkMemoryArrayRepo(), new ModelMemoryArrayRepo());

            case MEMORY_COLLECTION:{
                return null;
            }

            default: {
                return null;
            }
        }
    }
}
