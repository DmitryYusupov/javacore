package ru.yusdm.javacore.lesson15up16concurrency.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.repo.MarkRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.repo.impl.memory.MarkArrayRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.model.repo.impl.memory.ModelArrayRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.model.service.impl.ModelDefaultService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.order.repo.OrderRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.order.repo.impl.memory.OrderArrayRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.order.service.impl.OrderDefaultService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.repo.impl.memory.UserArrayRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.service.UserService;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.service.impl.UserDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory {

    private OrderRepo orderRepo = new OrderArrayRepo();
    private ModelRepo modelRepo = new ModelArrayRepo();
    private MarkRepo markRepo = new MarkArrayRepo();
    private UserRepo userRepo = new UserArrayRepo();

    private ModelService modelService = new ModelDefaultService(modelRepo, orderRepo);
    private OrderService orderService = new OrderDefaultService(orderRepo);
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
