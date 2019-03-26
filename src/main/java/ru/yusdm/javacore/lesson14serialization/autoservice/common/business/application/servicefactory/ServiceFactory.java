package ru.yusdm.javacore.lesson14serialization.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson14serialization.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson14serialization.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson14serialization.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
