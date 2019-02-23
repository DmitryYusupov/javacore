package ru.yusdm.javacore.lesson5oopinterface.autoservice.model.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;

public interface ModelService extends BaseService {

    void add(Model model);

    Model findById(long id);

    void delete(Model model);

}
