package ru.yusdm.javacore.lesson11io.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson11io.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson11io.autoservice.mark.repo.impl.memory.MarkCollectionRepo;
import ru.yusdm.javacore.lesson11io.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson11io.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson11io.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson11io.autoservice.model.repo.impl.memory.ModelCollectionRepo;
import ru.yusdm.javacore.lesson11io.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson11io.autoservice.model.service.impl.ModelDefaultService;
import ru.yusdm.javacore.lesson11io.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson11io.autoservice.order.repo.impl.memory.OrderCollectionRepo;
import ru.yusdm.javacore.lesson11io.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson11io.autoservice.order.service.impl.OrderDefaultService;
import ru.yusdm.javacore.lesson11io.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson11io.autoservice.user.repo.impl.memory.UserCollectionRepo;
import ru.yusdm.javacore.lesson11io.autoservice.user.service.UserService;
import ru.yusdm.javacore.lesson11io.autoservice.user.service.impl.UserDefaultService;

public class MemoryCollectionServiceFactory implements ServiceFactory {

    private OrderRepo orderRepo = new OrderCollectionRepo();
    private ModelRepo modelRepo = new ModelCollectionRepo();
    private MarkRepo markRepo = new MarkCollectionRepo();
    private UserRepo userRepo = new UserCollectionRepo();

    private ModelService modelService = new ModelDefaultService(modelRepo, orderRepo);
    private OrderService orderService = new OrderDefaultService(orderRepo);
    private UserService userService = new UserDefaultService(userRepo);
    private MarkService markService = new MarkDefaultService(markRepo, modelService, orderRepo);

    @Override
    public MarkService getMarkService() {
        return markService;
    }

    @Override
    public ModelService getModelService() {
        return modelService;
    }

    @Override
    public OrderService getOrderService() {
        return orderService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }
}
