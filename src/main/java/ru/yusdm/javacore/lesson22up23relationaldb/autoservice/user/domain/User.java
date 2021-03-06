package ru.yusdm.javacore.lesson22up23relationaldb.autoservice.user.domain;


import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.common.business.domain.BaseDomain;
import ru.yusdm.javacore.lesson22up23relationaldb.autoservice.order.domain.Order;

import java.util.List;

public class User extends BaseDomain<Long> {

    private String firstName;
    private String lastName;
    private int age;
    private ClientType clientType = ClientType.NEW;
    private List<Order> orders;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clientType='" + clientType + '\'' +
                '}';
    }
}
