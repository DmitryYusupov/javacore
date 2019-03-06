package ru.yusdm.javacore.lesson8setandcomparator.autoservice.common.business.application.servicefactory;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.mark.repo.impl.memory.MarkArrayRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.mark.service.MarkService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.mark.service.impl.MarkDefaultService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.repo.impl.memory.ModelArrayRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.service.ModelService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.model.service.impl.ModelDefaultService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.repo.impl.memory.OrderArrayRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.service.OrderService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.order.service.impl.OrderDefaultService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.user.repo.impl.memory.UserArrayRepo;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.user.service.UserService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.user.service.impl.UserDefaultService;

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
