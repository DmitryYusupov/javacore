package ru.yusdm.javacore.lesson9genericsbegin.singlegenericmethod;

import java.io.Serializable;

/**
 * Created by Admin on 3/6/2019.
 */
public class SingleGenericMethod {

    public String test(){
        return "asd";
    }

    //todo return to wild card demo!!!!
    public <T extends Comparable & Serializable, Zu> int compare(T t, T other, Zu zu){
        return t.compareTo(other);
    }


}
