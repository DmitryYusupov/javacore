package ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
