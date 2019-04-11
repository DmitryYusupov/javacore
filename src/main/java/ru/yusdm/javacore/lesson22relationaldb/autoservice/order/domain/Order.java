package ru.yusdm.javacore.lesson22relationaldb.autoservice.order.domain;


import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.domain.BaseDomain;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.user.domain.User;

import java.util.Optional;

public class Order extends BaseDomain<Long> {


    private Long markId;
    private Long modelId;
    private Long userId;

    private String description;
    private int price;

    public Order() {
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
