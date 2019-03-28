package ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.repo.impl.memory;

import ru.yusdm.javacore.lesson15up16concurrency.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.storage.SequenceGenerator;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson15up16concurrency.autoservice.user.search.UserSearchCondition;

import java.util.List;

import static ru.yusdm.javacore.lesson15up16concurrency.autoservice.storage.Storage.usersList;


public class UserCollectionRepo implements UserRepo {

    @Override
    public void insert(User user) {
        user.setId(SequenceGenerator.getNextValue());
        usersList.add(user);
    }

    @Override
    public void update(User user) {
        //we already in memory, no need to update object
    }

    @Override
    public User findById(Long id) {
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
        User found = findUserById(id);

        if (found != null) {
            usersList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (User user : usersList) {
            System.out.println(user);
        }
    }

    private User findUserById(long userId) {
        for (User user : usersList) {
            if (Long.valueOf(userId).equals(user.getId())) {
                return user;
            }
        }
        return null;
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