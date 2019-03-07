package ru.yusdm.javacore.lesson5oopinterface.lesson.atmwithinterface;

/**
 * Created by Admin on 2/22/2019.
 */
public class GoodAtm implements Atm, Repairable {

    private int maxCashCanBeReceivedByUser = 100;

    @Override
    public void sentCash(int cash) {

    }

    @Override
    public float getCash(int cash) {
        if (cash > maxCashCanBeReceivedByUser) {
            return -1;
        } else {
            return cash;
        }
    }


}
