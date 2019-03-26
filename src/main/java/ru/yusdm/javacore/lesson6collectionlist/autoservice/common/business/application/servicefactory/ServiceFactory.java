package ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
