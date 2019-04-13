package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
