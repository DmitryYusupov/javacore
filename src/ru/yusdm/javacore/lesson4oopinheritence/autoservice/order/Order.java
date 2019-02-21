package ru.yusdm.javacore.lesson4oopinheritence.autoservice.order;


import ru.yusdm.javacore.lesson4oopinheritence.autoservice.mark.Mark;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.model.Model;
import ru.yusdm.javacore.lesson4oopinheritence.autoservice.user.User;

public class Order {
    private Long id;
    private Mark mark;
    private Model model;
    private User user;
    private String description;
    private int price;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
