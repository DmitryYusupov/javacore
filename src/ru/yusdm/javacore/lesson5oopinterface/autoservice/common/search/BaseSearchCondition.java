package ru.yusdm.javacore.lesson5oopinterface.autoservice.common.search;

public abstract class BaseSearchCondition {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
