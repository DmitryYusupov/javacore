package ru.yusdm.javacore.lesson5oopinterface.autoservice.order.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.repo.impl.OrderMemoryArrayRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.service.impl.OrderDefaultService;

public final class OrderServiceCreator {

    private OrderServiceCreator(){

    }

    public static OrderService getOrderService(StorageType storageType) {
        switch (storageType) {

            case MEMORY_ARRAY:
                return new OrderDefaultService(new OrderMemoryArrayRepo());

            case MEMORY_COLLECTION:{
                return null;
            }

            default: {
                return null;
            }
        }
    }
}
