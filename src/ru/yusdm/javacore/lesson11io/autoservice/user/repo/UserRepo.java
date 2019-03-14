package ru.yusdm.javacore.lesson11io.autoservice.user.repo;

import ru.yusdm.javacore.lesson11io.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson11io.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson11io.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<User> search(UserSearchCondition searchCondition);
}
