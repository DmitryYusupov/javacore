package ru.yusdm.javacore.lesson17java8.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson17java8.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson17java8.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson17java8.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson17java8.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
