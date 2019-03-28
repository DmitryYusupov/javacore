package ru.yusdm.javacore.lesson15up16concurrency.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
