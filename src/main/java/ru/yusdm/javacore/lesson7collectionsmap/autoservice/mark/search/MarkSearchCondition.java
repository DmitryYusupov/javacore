package ru.yusdm.javacore.lesson7collectionsmap.autoservice.mark.search;

import ru.yusdm.javacore.lesson7collectionsmap.autoservice.common.business.search.BaseSearchCondition;

public class MarkSearchCondition extends BaseSearchCondition {

    private String name;
    private String country;

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

    public boolean needSearchByCountry(){
        return true;
    }

    public boolean isNameLengthGeThan6Chars(){
        return true;
    }
}
