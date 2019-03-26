package ru.yusdm.javacore.lesson6collectionlist.autoservice.user.repo;

import ru.yusdm.javacore.lesson6collectionlist.autoservice.common.business.repo.BaseRepo;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson6collectionlist.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo {
    void add(User user);

    User findById(long id);

    List<User> search(UserSearchCondition searchCondition);

}
