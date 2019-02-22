package ru.yusdm.javacore.lesson5oopinterface.atmwithinterface;


/**
 * Created by Admin on 2/22/2019.
 */
public class BadAtm implements Atm {

    private int maxCashCanBeReceivedByUser = 50;

    @Override
    public void sentCash(int cash) {

    }

    @Override
    public float getCash(int cash) {
        if (cash > maxCashCanBeReceivedByUser) {
            return -1;
        } else {
            return cash * (1- RATE);
        }
    }
}
