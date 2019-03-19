package ru.yusdm.javacore.lesson12xml.autoservice.common.business.search;

import ru.yusdm.javacore.lesson12xml.autoservice.common.business.search.OrderDirection;
import ru.yusdm.javacore.lesson12xml.autoservice.common.business.search.OrderType;

public abstract class BaseSearchCondition<ID> {
    protected ID id;
    protected ru.yusdm.javacore.lesson12xml.autoservice.common.business.search.OrderDirection orderDirection;
    protected ru.yusdm.javacore.lesson12xml.autoservice.common.business.search.OrderType orderType = ru.yusdm.javacore.lesson12xml.autoservice.common.business.search.OrderType.SIMPLE;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ru.yusdm.javacore.lesson12xml.autoservice.common.business.search.OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
    }

    public ru.yusdm.javacore.lesson12xml.autoservice.common.business.search.OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public boolean needOrdering() {
        return orderDirection != null && orderType != null;
    }
}
