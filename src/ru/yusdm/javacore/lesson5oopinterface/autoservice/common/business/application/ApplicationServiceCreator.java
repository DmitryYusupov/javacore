package ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.application;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.service.MarkServiceCreator;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.service.ModelServiceCreator;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.order.service.OrderServiceCreator;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service.UserService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service.UserServiceCreator;

public final class ApplicationServiceCreator {

    private ApplicationServiceCreator() {

    }

    public static MarkService getMarkService(StorageType storageType) {
        return MarkServiceCreator.getMarkService(storageType);
    }

    public static ModelService getModelService(StorageType storageType) {
        return ModelServiceCreator.getModelService(storageType);
    }

    public static OrderService getOrderService(StorageType storageType) {
        return OrderServiceCreator.getOrderService(storageType);
    }

    public static UserService getUserService(StorageType storageType) {
        return UserServiceCreator.getUserService(storageType);
    }
}
