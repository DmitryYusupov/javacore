package ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.repo.impl.memory;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.SequenceGenerator;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.search.UserSearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.Storage.usersList;


public class UserCollectionRepo implements UserRepo {

    @Override
    public User insert(User user) {
        user.setId(SequenceGenerator.getNextValue());
        usersList.add(user);

        return user;
    }

    @Override
    public void insert(Collection<User> users) {
        for (User user : users) {
            insert(user);
        }
    }

    @Override
    public void update(User user) {
        //we already in memory, no need to update object
    }

    @Override
    public Optional<User> findById(Long id) {
        return findUserById(id);
    }

    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        List<? extends User> users = doSearch(searchCondition);

        if (!users.isEmpty() && searchCondition.shouldPaginate()) {
            users = getPageableData(users, searchCondition.getPaginator());
        }

        return users;
    }

    private List<? extends User> getPageableData(List<? extends User> users, Paginator paginator) {
        return CollectionUtils.getPageableData(users, paginator.getLimit(), paginator.getOffset());
    }

    private List<User> doSearch(UserSearchCondition searchCondition) {
        return usersList;
    }

    @Override
    public void deleteById(Long id) {
        findUserById(id).map(user -> usersList.remove(user));
    }

    @Override
    public void printAll() {
        for (User user : usersList) {
            System.out.println(user);
        }
    }

    private Optional<User> findUserById(long userId) {
        for (User user : usersList) {
            if (Long.valueOf(userId).equals(user.getId())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return usersList;
    }

    @Override
    public int countAll() {
        return usersList.size();
    }
}