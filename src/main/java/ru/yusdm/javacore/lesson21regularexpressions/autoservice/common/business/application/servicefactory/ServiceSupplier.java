package ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.user.service.UserService;

public final class ServiceSupplier {
    private static volatile ServiceSupplier INSTANCE;
    private ServiceFactory serviceFactory;

    public static ServiceSupplier getInstance() {
        return INSTANCE;
    }

    public static ServiceSupplier newInstance(StorageType storageType) {

        if (INSTANCE == null) {
            synchronized (ServiceSupplier.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceSupplier(storageType);
                }
            }
        }
        return INSTANCE;
    }

    private ServiceSupplier(StorageType storageType) {
        initServiceFactory(storageType);
    }

    private void initServiceFactory(StorageType storageType) {
        switch (storageType) {
            case MEMORY_ARRAY: {
                serviceFactory = new MemoryArrayServiceFactory();
            }
            default: {
                serviceFactory = new MemoryCollectionServiceFactory();
            }
        }
    }

    public MarkService getMarkService() {
        return serviceFactory.getMarkService();
    }

    public ModelService getModelService() {
        return serviceFactory.getModelService();
    }

    public OrderService getOrderService() {
        return serviceFactory.getOrderService();
    }

    public UserService getUserService() {
        return serviceFactory.getUserService();
    }
}
