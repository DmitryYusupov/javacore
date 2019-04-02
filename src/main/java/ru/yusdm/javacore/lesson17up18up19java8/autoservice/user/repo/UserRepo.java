package ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.repo;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<? extends User> search(UserSearchCondition searchCondition);
}
