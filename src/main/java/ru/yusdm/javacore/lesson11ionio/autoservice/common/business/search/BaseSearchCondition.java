package ru.yusdm.javacore.lesson11ionio.autoservice.common.business.search;

public abstract class BaseSearchCondition<ID> {
    protected ID id;
    protected OrderDirection orderDirection;
    protected OrderType orderType = OrderType.SIMPLE;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public boolean needOrdering() {
        return orderDirection != null && orderType != null;
    }
}
