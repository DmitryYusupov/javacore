package ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.service;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson7collectionsmap.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService {

    void add(User user);

    User findById(Long id);

    void delete(User user);

    List<User> search(UserSearchCondition searchCondition);

}
