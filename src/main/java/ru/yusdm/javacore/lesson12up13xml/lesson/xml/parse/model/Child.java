package ru.yusdm.javacore.lesson12up13xml.lesson.xml.parse.model;

/**
 * Created by Admin on 3/18/2019.
 */
public class Child {

//    JAXB
//
//    1) schema.xsd
//    2) Java classes
//    3) Marshalling/demarshlling

    //GSon
    private String name;
    private String school;
    private int age;
    private String hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", school='" + school + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
