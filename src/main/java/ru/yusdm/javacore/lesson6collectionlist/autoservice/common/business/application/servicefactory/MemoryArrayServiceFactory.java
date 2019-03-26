package ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.repo.impl.MarkMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.repo.impl.ModelMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.service.impl.ModelDefaultService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.repo.impl.OrderMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service.impl.OrderDefaultService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.repo.impl.UserMemoryArrayRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.service.UserService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.service.impl.UserDefaultService;

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
