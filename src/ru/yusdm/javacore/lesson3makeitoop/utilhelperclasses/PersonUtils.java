package ru.yusdm.javacore.lesson3makeitoop.utilhelperclasses;


import ru.yusdm.javacore.lesson2oopclasses.createobjects.complexobject.Person;

public class PersonUtils {

    private PersonUtils(){

    }

    public static void smartPrint(Person person) {
        System.out.println("Smart print");
    }

    public static void saveToFile(Person person) {
        System.out.println("Save to file");
    }

}
