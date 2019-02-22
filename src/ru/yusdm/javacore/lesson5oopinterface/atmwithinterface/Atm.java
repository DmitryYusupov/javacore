package ru.yusdm.javacore.lesson5oopinterface.atmwithinterface;

public interface Atm {

    float RATE = 0.1f;

    public static final float RATE2 = 2f;

    void sentCash(int cash);

    float getCash(int cash);

}

