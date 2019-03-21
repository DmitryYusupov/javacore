package ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.model;

import java.util.List;

/**
 * Created by Admin on 3/18/2019.
 */
public class Person {

    private String name;
    private String lastName;
    private List<Child> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", \nchildren=" + childrenToString() +
                '}';
    }

    private String childrenToString(){
        StringBuilder stringBuilder = new StringBuilder();

        for (Child child: children){
            stringBuilder.append(child);
        }

        return stringBuilder.toString();
    }
}
