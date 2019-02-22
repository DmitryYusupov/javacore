package ru.yusdm.javacore.lesson5oopinterface.enumtest.simpleenum;

import static ru.yusdm.javacore.lesson5oopinterface.enumtest.simpleenum.Climate.ARCTIC;

/**
 * Created by Admin on 2/22/2019.
 */
public class Country {
    private Long id;
    private String name;
    private Climate climate;

    public Country(Long id, String name, Climate climate) {
        this.id = id;
        this.name = name;
        this.climate = climate;
    }

    public static void main(String[] args) {
        new Country(1L, "Russia", ARCTIC);
        System.out.println("---------Simple demo-------");
        System.out.println(ARCTIC.name());
        System.out.println(ARCTIC.toString());
        System.out.println(ARCTIC.ordinal());
        System.out.println("---------All enum values-------");

        for (Climate climate: Climate.values()){
            System.out.println(climate + " " + climate.ordinal());
        }

        System.out.println("---------Ordinal demo-------");
        Climate climate = Climate.ARCTIC;
        if (climate.ordinal() == 3){
            System.out.println("Very cold");
        }

        System.out.println("---------Compare demo-------");
        climate = Climate.ARCTIC;
        demoCompare(null);
    }

    private static void demoCompare(Climate climate){
        if (Climate.ARCTIC.equals(climate)){
            System.out.println("Very cold");
        }
    }
}
