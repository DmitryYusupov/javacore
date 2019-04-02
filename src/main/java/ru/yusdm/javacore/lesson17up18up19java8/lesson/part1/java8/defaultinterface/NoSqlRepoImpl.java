package ru.yusdm.javacore.lesson17up18up19java8.lesson.part1.java8.defaultinterface;

import ru.yusdm.javacore.lesson5oopinterface.lesson.dataclass.Person;

/**
 * Created by Admin on 3/29/2019.
 */
public class NoSqlRepoImpl implements Repository<Person>{
    @Override
    public void add(Person person) {
    }
    @Override
    public Person get() {
        return null;
    }
    @Override
    public void delete(Person person) {

    }
}
