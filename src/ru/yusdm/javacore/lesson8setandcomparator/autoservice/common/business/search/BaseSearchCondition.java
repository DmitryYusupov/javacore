package ru.yusdm.javacore.lesson8setandcomparator.autoservice.common.business.search;

public abstract class BaseSearchCondition {
    protected Long id;
    protected SortType sortType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
