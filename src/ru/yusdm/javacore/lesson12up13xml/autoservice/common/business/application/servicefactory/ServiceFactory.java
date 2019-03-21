package ru.yusdm.javacore.lesson12up13xml.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson12up13xml.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson12up13xml.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson12up13xml.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson12up13xml.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
