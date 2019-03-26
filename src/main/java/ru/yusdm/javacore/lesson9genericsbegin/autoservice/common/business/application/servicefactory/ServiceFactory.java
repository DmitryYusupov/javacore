package ru.yusdm.javacore.lesson9genericsbegin.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson9genericsbegin.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson9genericsbegin.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
