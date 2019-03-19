package ru.yusdm.javacore.lesson12xml.autoservice.order.domain;


import ru.yusdm.javacore.lesson12xml.autoservice.common.business.domain.BaseDomain;
import ru.yusdm.javacore.lesson12xml.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson12xml.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson12xml.autoservice.user.domain.User;

public class Order extends BaseDomain<Long> {
    private Mark mark;
    private Model model;
    private User user;
    private String description;
    private int price;

    public Order() {
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
