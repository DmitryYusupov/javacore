package ru.yusdm.javacore.lesson24web.autoservice.user.repo.impl.memory;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.utils.ArrayUtils;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson24web.autoservice.common.solutions.utils.OptionalUtils;
import ru.yusdm.javacore.lesson24web.autoservice.storage.SequenceGenerator;
import ru.yusdm.javacore.lesson24web.autoservice.user.domain.User;
import ru.yusdm.javacore.lesson24web.autoservice.user.repo.UserRepo;
import ru.yusdm.javacore.lesson24web.autoservice.user.search.UserSearchCondition;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.yusdm.javacore.lesson24web.autoservice.storage.Storage.usersArray;


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
        users.forEach(this::insert);
    }

    @Override
    public void update(User user) {
        //we already in memory, no need to update object
    }

    @Override
    public Optional<User> findById(Long id) {
        return findUserIndexById(id).map(userInd -> usersArray[userIndex]);
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
        return Arrays.stream(usersArray).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private List<? extends User> getPageableData(List<? extends User> users, Paginator paginator) {
        return CollectionUtils.getPageableData(users, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void deleteById(Long id) {
        findUserIndexById(id).ifPresent(this::deleteUserByIndex);
    }

    private void deleteUserByIndex(int index) {
        ArrayUtils.removeElement(usersArray, index);
        userIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(usersArray).filter(Objects::nonNull).forEach(System.out::println);
    }

    private Optional<Integer> findUserIndexById(long userId) {
        OptionalInt optionalInt = IntStream.range(0, usersArray.length).filter(i ->
                usersArray[i] != null && Long.valueOf(userId).equals(usersArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<User> findAll() {
        return Arrays.asList(usersArray).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public int countAll() {
        return usersArray.length;
    }
}
