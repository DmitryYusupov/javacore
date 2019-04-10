package ru.yusdm.javacore.lesson22relationaldb.autoservice.user.repo.impl;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.exception.SqlError;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.solutions.repo.jdbc.QueryWrapper;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.search.UserSearchCondition;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserDefaultRepoImpl implements UserRepo {
    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        return null;
    }

    @Override
    public User insert(User entity) {
        return null;
    }

    @Override
    public void insert(Collection<User> items) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM USER",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (SQLException e) {
            throw new SqlError(e);
        }

    }
}
