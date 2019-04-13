package ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson24web.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson24web.autoservice.mark.repo.impl.memory.MarkCollectionRepo;
import ru.yusdm.javacore.lesson24web.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson24web.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson24web.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson24web.autoservice.model.repo.impl.memory.ModelCollectionRepo;
import ru.yusdm.javacore.lesson24web.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson24web.autoservice.model.service.impl.ModelDefaultService;
import ru.yusdm.javacore.lesson24web.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson24web.autoservice.order.repo.impl.memory.OrderCollectionRepo;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson24web.autoservice.order.service.impl.OrderDefaultService;
import ru.yusdm.javacore.lesson24web.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson24web.autoservice.user.repo.impl.memory.UserCollectionRepo;
import ru.yusdm.javacore.lesson24web.autoservice.user.service.UserService;
import ru.yusdm.javacore.lesson24web.autoservice.user.service.impl.UserDefaultService;

public class MemoryCollectionServiceFactory implements ServiceFactory {

    private OrderRepo orderRepo = new OrderCollectionRepo();
    private ModelRepo modelRepo = new ModelCollectionRepo();
    private MarkRepo markRepo = new MarkCollectionRepo();
    private UserRepo userRepo = new UserCollectionRepo();

    private ModelService modelService = new ModelDefaultService(modelRepo, orderRepo);
    private OrderService orderService = new OrderDefaultService(orderRepo, markRepo, modelRepo, userRepo);
    private UserService userService = new UserDefaultService(userRepo, orderService);
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
