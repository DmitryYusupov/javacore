package ru.yusdm.javacore.lesson5oopinterface.button;

/**
 * Created by Admin on 2/22/2019.
 */
public class SaveButtonHandler implements
        Button.OnClickHandler{
    @Override
    public void handle() {
        System.out.println("Save document");
    }
}
