package ru.yusdm.javacore.lesson4oopinheritence.autoservice.mark;


import ru.yusdm.javacore.lesson4oopinheritence.autoservice.model.Model;

public class Mark {

    private Long id;
    private String name;
    private Model[] models;

    public Mark() {
    }

    public Mark(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Model[] getModels() {
        return models;
    }

    public void setModels(Model[] models) {
        this.models = models;
    }
}
