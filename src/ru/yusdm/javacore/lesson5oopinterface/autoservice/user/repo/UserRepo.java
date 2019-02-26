package ru.yusdm.javacore.lesson5oopinterface.autoservice.user.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson5oopinterface.autoservice.user.search.UserSearchCondition;

public interface UserRepo extends BaseRepo {
    void add(User user);

    User findById(long id);

    User[] search(UserSearchCondition searchCondition);

}
