package ru.yusdm.javacore.lesson8setandcomparator.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.user.service.UserService;

public interface ServiceFactory {
    MarkService getMarkService();
    ModelService getModelService();
    OrderService getOrderService();
    UserService getUserService();
}
