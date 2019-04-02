package ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.service;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    List<? extends User> search(UserSearchCondition searchCondition);
}
