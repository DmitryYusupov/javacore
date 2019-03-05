package ru.yusdm.javacore.lesson8setandcomparator.autoservice.user.service;

import ru.yusdm.javacore.lesson8setandcomparator.autoservice.common.business.service.BaseService;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson8setandcomparator.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService {

    void add(User user);

    User findById(Long id);

    void delete(User user);

    List<User> search(UserSearchCondition searchCondition);

    void update(User user);
}
