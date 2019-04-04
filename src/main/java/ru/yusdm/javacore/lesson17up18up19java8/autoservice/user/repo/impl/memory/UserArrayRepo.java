package ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.repo.impl.memory;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.SequenceGenerator;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson17up18up19java8.autoservice.user.search.UserSearchCondition;

import java.util.*;

import static ru.yusdm.javacore.lesson17up18up19java8.autoservice.storage.Storage.usersArray;


public class UserArrayRepo implements UserRepo {
    private int userIndex = -1;

    @Override
    public User insert(User user) {
        if (userIndex == usersArray.length - 1) {
            User[] newArrUsers = new User[usersArray.length * 2];
            System.arraycopy(usersArray, 0, newArrUsers, 0, usersArray.length);
            usersArray = newArrUsers;
        }

        userIndex++;
        user.setId(SequenceGenerator.getNextValue());
        usersArray[userIndex] = user;

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
        Integer userIndex = findUserIndexById(id);
        if (userIndex != null) {
            return Optional.of(usersArray[userIndex]);
        }

        return Optional.empty();
    }

    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        List<? extends User> users = doSearch(searchCondition);

        if (!users.isEmpty() && searchCondition.shouldPaginate()) {
            users = getPageableData(users, searchCondition.getPaginator());
        }

        return users;
    }

    private List<User> doSearch(UserSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    private List<? extends User> getPageableData(List<? extends User> users, Paginator paginator) {
        return CollectionUtils.getPageableData(users, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void deleteById(Long id) {
        Integer userIndex = findUserIndexById(id);

        if (userIndex != null) {
            deleteUserByIndex(userIndex);
        }
    }

    private void deleteUserByIndex(int index) {
        ArrayUtils.removeElement(usersArray, index);
        userIndex--;
    }

    @Override
    public void printAll() {
        for (User user : usersArray) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    private Integer findUserIndexById(long userId) {
        for (int i = 0; i < usersArray.length; i++) {
            if (usersArray[i] != null && Long.valueOf(userId).equals(usersArray[i].getId())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(Arrays.asList(usersArray));
    }

    @Override
    public int countAll() {
        return usersArray.length;
    }
}
