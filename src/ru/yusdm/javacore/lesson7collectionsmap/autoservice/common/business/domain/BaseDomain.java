package ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.business.domain;

public abstract class BaseDomain {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
