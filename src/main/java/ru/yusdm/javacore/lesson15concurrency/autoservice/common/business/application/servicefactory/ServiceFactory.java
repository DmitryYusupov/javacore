package ru.yusdm.javacore.lesson15concurrency.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson15concurrency.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson15concurrency.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson15concurrency.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson15concurrency.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
