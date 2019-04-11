package ru.yusdm.javacore.lesson22relationaldb.autoservice.mark.domain;


import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.domain.BaseDomain;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.Model;

import java.util.List;

public class Mark extends BaseDomain<Long> {
    private String name;
    private String country;
    private List<Model> models;

    public Mark() {
    }

    public Mark(Long id) {
        this.id = id;
    }

    public Mark(String name) {
        this.name = name;
    }

    public Mark(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "-----------------------\nMark\n" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", \n\nmodels:\n" + getModelsAsStr();
    }

    private String getModelsAsStr() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Model model : models) {
            stringBuilder.append(model.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    public String getAsStrWithoutModles() {
        return "id=" + id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'';

    }
}
