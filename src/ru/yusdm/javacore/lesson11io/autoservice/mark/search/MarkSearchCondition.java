package ru.yusdm.javacore.lesson11io.autoservice.mark.search;

import ru.yusdm.javacore.lesson11io.autoservice.common.business.search.BaseSearchCondition;

public class MarkSearchCondition extends BaseSearchCondition<Long> {

    private String name;
    private String country;
    private MarkOrderByField orderByField;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MarkOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(MarkOrderByField orderByField) {
        this.orderByField = orderByField;
    }

    public boolean needOrdering() {
        return super.needOrdering() && orderByField != null;
    }


}
