package ru.yusdm.javacore.lesson17up18up19java8.autoservice.order.search;

import ru.yusdm.javacore.lesson17up18up19java8.autoservice.common.business.search.BaseSearchCondition;

public class OrderSearchCondition extends BaseSearchCondition<Long> {
    private Long userId;
    private Long markId;
    private Long modelId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean searchByUserId() {
        return userId != null;
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public boolean searchByMarkId() {
        return markId != null;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public boolean searchByModelId() {
        return modelId != null;
    }
}
