package sean.yu.awttest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 18:21
 **/

public class TestActionEvent extends MyBaseFrame {

    public static void main(String[] args) {
        Frame frame = getInstance();
        Button button = new Button();

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("button was clicked");
            }
        });

        frame.add(button,BorderLayout.CENTER);
    }
}
