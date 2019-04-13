package ru.yusdm.javacore.lesson24web.autoservice.user.repo;

import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.repo.BaseRepo;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<? extends User> search(UserSearchCondition searchCondition);
}
