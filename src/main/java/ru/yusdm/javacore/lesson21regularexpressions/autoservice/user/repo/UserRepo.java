package ru.yusdm.javacore.lesson21regularexpressions.autoservice.user.repo;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<? extends User> search(UserSearchCondition searchCondition);
}
