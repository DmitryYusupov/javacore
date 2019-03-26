package ru.yusdm.javacore.lesson2oopclasses.lesson.initorder.success;

public class Person {

    static {
        System.out.println("This is static block");
    }

    private static String citizen = getCitizen();
    private Integer age = getIncAge();
    private String name;
    {
        System.out.println("Non static init block");
    }

    public Person() {
        System.out.println("Constructor");
    }

    private static String getCitizen() {
        System.out.println("Get citizen (static call)");
        return "Russia";
    }


    public int getIncAge() {
        System.out.println("Get inc Age (NON static call)");
        return age == null ? 0 : age++;
    }

    public static void main(String[] args) {
        System.out.println("Main call");
        System.out.println("------------------------");
        new Person();
        System.out.println("------------------------");
        new Person();

    }

}
