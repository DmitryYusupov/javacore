package ru.yusdm.javacore.lesson5oopinterface.autoservice.model.service;

import ru.yusdm.javacore.lesson5oopinterface.autoservice.model.domain.Model;

public interface ModelService {

    void add(Model model);

    Model findById(long id);

    void delete(Model model);

    void deleteById(long id);

    void printAll();

}
