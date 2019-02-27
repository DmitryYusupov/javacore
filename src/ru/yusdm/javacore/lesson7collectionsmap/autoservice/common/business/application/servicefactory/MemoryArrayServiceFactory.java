package ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.repo.impl.MarkMemoryArrayRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.repo.impl.ModelMemoryArrayRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.model.service.impl.ModelDefaultService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.repo.impl.OrderMemoryArrayRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.order.service.impl.OrderDefaultService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.repo.impl.UserMemoryArrayRepo;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.service.UserService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.service.impl.UserDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory {

    @Override
    public MarkService getMarkService() {
        return new MarkDefaultService(new MarkMemoryArrayRepo(), new ModelMemoryArrayRepo());
    }

    @Override
    public ModelService getModelService() {
        return new ModelDefaultService(new ModelMemoryArrayRepo());
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderMemoryArrayRepo());
    }

    @Override
    public UserService getUserService() {
        return new UserDefaultService(new UserMemoryArrayRepo());
    }
}
