package ru.yusdm.javacore.lesson5oopinterface.autoservice.model.repo;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;

public interface ModelRepo {

    void add(Model model);

    Model findById(long id);

    void delete(Model model);

    void deleteById(long id);

    void printAll();

}
