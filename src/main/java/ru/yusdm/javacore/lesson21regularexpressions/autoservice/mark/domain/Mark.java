package ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.domain;


import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.business.domain.BaseDomain;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.Model;

import java.util.List;

public class Mark extends BaseDomain<Long> {
    private String name;
    private String country;
    private List<Model> models;

    public Mark() {
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
                ", name='" + name + '\'' ;

    }
    /*
    1|Order |101
    2|Order |202

    101. Russia
    202. Belarus
    */

    /*
    1 |Order
    27|Order

    101|1| Russia
    202|1| Belarus

    401|27| Russia
    502|27| Belarus



    SELECT * FROM
	 MARK mk, MODEL md
	 WHERE
	 (mk.NAME = 'Ford') AND (md.MARK_ID = mk.ID)

    */
}
