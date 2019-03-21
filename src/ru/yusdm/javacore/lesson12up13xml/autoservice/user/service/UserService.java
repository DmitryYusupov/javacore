package ru.yusdm.javacore.lesson12up13xml.autoservice.user.service;

import ru.yusdm.javacore.lesson12up13xml.autoservice.common.solutions.service.BaseService;
import ru.yusdm.javacore.lesson12up13xml.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson12up13xml.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    List<User> search(UserSearchCondition searchCondition);
}
