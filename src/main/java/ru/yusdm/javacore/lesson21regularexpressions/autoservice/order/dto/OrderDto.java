package ru.yusdm.javacore.lesson21regularexpressions.autoservice.order.dto;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.common.business.dto.BaseDto;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.dto.MarkDto;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.dto.ModelDto;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.user.dto.UserDto;

public class OrderDto extends BaseDto<Long> {

    private MarkDto mark;
    private ModelDto model;
    private UserDto user;
    private String description;
    private int price;

    public OrderDto() {
    }

    public MarkDto getMark() {
        return mark;
    }

    public void setMark(MarkDto mark) {
        this.mark = mark;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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
