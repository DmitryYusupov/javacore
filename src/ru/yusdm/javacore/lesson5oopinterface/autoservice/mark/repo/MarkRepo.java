package ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.mark.domain.Mark;

public interface MarkRepo {
    void add(Mark mark);

    Mark findById(long id);

    void delete(Mark mark);

    void deleteById(long id);

    void printAll();
}
