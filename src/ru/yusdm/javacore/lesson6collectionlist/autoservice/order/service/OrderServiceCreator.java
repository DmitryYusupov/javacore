package ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.repo.impl.OrderMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.repo.impl.OrderMemoryCollectionRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service.impl.OrderDefaultService;

public final class OrderServiceCreator {

    private OrderServiceCreator(){

    }

    public static OrderService getOrderService(StorageType storageType) {
        switch (storageType) {

            case MEMORY_ARRAY:
                return new OrderDefaultService(new OrderMemoryArrayRepo());

            case MEMORY_COLLECTION:{
                return new OrderDefaultService(new OrderMemoryCollectionRepo());
            }

            default: {
                return null;
            }
        }
    }
}
