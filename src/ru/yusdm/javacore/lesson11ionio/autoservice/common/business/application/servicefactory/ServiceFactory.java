package ru.yusdm.javacore.lesson11ionio.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson11ionio.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson11ionio.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson11ionio.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson11ionio.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
