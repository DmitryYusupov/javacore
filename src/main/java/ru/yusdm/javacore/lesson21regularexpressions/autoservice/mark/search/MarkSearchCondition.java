package ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.search;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.business.search.BaseSearchCondition;

import static ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.solutions.utils.StringUtils.isNotBlank;

public class MarkSearchCondition extends BaseSearchCondition<Long> {

    private String name;
    private String country;
    private MarkOrderByField orderByField;

    public boolean searchByName() {
        return isNotBlank(name);
    }

    public boolean searchByCountry() {
        return isNotBlank(country);
    }

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
