package ru.yusdm.javacore.lesson5oopinterface.lesson.swingbutton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Admin on 2/22/2019.
 */
public class SwingButtonDemo {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(new Dimension(800, 600));
        jFrame.setLayout(new FlowLayout());

        JButton helloWorldButton = new JButton("Click me");
        helloWorldButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("HELLO WORLD");
            }
        });
        jFrame.add(helloWorldButton);
        JButton button = new JButton("OTHER");
        button.addActionListener(new OtherHandler());
        jFrame.add(button);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private static class OtherHandler extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Other button click");
        }
    }
}
