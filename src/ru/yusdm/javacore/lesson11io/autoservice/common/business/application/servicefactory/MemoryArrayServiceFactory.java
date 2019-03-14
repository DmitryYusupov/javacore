package ru.yusdm.javacore.lesson11io.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson11io.autoservice.mark.repo.impl.memory.MarkArrayRepo;
import ru.yusdm.javacore.lesson11io.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson11io.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson11io.autoservice.model.repo.impl.memory.ModelArrayRepo;
import ru.yusdm.javacore.lesson11io.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson11io.autoservice.model.service.impl.ModelDefaultService;
import ru.yusdm.javacore.lesson11io.autoservice.order.repo.impl.memory.OrderArrayRepo;
import ru.yusdm.javacore.lesson11io.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson11io.autoservice.order.service.impl.OrderDefaultService;
import ru.yusdm.javacore.lesson11io.autoservice.user.repo.impl.memory.UserArrayRepo;
import ru.yusdm.javacore.lesson11io.autoservice.user.service.UserService;
import ru.yusdm.javacore.lesson11io.autoservice.user.service.impl.UserDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory {

    @Override
    public MarkService getMarkService() {
        return new MarkDefaultService(new MarkArrayRepo(), new ModelArrayRepo());
    }

    @Override
    public ModelService getModelService() {
        return new ModelDefaultService(new ModelArrayRepo());
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderArrayRepo());
    }

    @Override
    public UserService getUserService() {
        return new UserDefaultService(new UserArrayRepo());
    }
}
