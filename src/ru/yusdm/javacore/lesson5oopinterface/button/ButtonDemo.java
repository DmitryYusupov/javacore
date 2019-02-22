package ru.yusdm.javacore.lesson5oopinterface.button;

/**
 * Created by Admin on 2/22/2019.
 */
public class ButtonDemo {

    public static void main(String[] args) {

        Button button = new Button("Save");
        button.onClick(new SaveButtonHandler());
        button.simulateClick();

        button = new Button("Print");
        button.onClick(new Button.OnClickHandler() {
            @Override
            public void handle() {
                System.out.println("Print clicked");
            }
        });
        button.simulateClick();

    }
}
