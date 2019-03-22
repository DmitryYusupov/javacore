package ru.yusdm.javacore.lesson14serialization.autoservice.user.repo;

import ru.yusdm.javacore.lesson14serialization.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson14serialization.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson14serialization.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<User> search(UserSearchCondition searchCondition);
}
