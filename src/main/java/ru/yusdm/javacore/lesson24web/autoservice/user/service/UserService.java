package ru.yusdm.javacore.lesson24web.autoservice.user.service;

import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    List<? extends User> search(UserSearchCondition searchCondition);
}
