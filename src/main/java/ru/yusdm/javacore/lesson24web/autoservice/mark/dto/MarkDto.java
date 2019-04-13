package ru.yusdm.javacore.lesson24web.autoservice.mark.dto;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.dto.BaseDto;
import ru.yusdm.javacore.lesson24web.autoservice.model.dto.ModelDto;

import java.util.List;

public class MarkDto extends BaseDto<Long> {
    private String name;
    private String country;
    private List<ModelDto> models;

    public MarkDto() {
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

    public List<ModelDto> getModels() {
        return models;
    }

    public void setModels(List<ModelDto> models) {
        this.models = models;
    }
}
