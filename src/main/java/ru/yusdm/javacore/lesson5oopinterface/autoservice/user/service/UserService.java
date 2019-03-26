package ru.yusdm.javacore.lesson5oopinterface.autoservice.user.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.domain.User;

public interface UserService extends BaseService {

    void add(User user);

    User findById(Long id);

    void delete(User user);

}
