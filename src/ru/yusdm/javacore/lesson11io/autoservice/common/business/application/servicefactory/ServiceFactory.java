package ru.yusdm.javacore.lesson11io.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson11io.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson11io.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson11io.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson11io.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
