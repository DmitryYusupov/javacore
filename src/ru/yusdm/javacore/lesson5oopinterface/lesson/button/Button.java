package ru.yusdm.javacore.lesson5oopinterface.lesson.button;

/**
 * Created by Admin on 2/22/2019.
 */
public class Button {

    private String caption;
    private OnClickHandler listener;

    public Button(String caption) {
        this.caption = caption;
    }

    public void simulateClick() {
        listener.handle();
    }

    public static interface OnClickHandler{
        void handle();
    }

    public void onClick(OnClickHandler onClickHandler){
        listener = onClickHandler;
    }

}
