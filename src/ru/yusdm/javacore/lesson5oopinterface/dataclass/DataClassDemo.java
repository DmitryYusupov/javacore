package ru.yusdm.javacore.lesson5oopinterface.dataclass;

/**
 * Created by Admin on 2/22/2019.
 */
public class DataClassDemo {

    public static void main(String[] args) {
        Person person = new Person("Dmitry",
                "Yusupov",new Passport("11", "22"));
        System.out.println(person);


        person.getPassport().setNumber("NEW NUMBER");
        System.out.println(person);
    }
}
