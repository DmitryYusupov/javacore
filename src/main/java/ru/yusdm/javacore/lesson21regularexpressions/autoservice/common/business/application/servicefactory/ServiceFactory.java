package ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
