package ru.yusdm.javacore.lesson17java8.autoservice.common.business.domain;

public abstract class BaseDomain<ID> {
    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
