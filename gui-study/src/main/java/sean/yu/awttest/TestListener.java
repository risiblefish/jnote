package sean.yu.awttest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 21:44
 **/
public class TestListener extends MyBaseFrame {
    /**
     * 测试，让同一个监听器应用到多个Button上
     * @param args
     */

    static final String BUTTON_1_COMMAND = "btn1";
    static final String BUTTON_2_COMMAND = "btn2";

    public static void main(String[] args) {
        Frame frame = getInstance();

        Button button1 = new Button("b1");
        Button button2 = new Button("b2");

        button1.setActionCommand(BUTTON_1_COMMAND);
        button2.setActionCommand(BUTTON_2_COMMAND);

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(1,1));
        panel.add(button1);
        panel.add(button2);

        ActionListener myListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case BUTTON_1_COMMAND : {
                        System.out.println("button1 clicked");
                        break;
                    }
                    case BUTTON_2_COMMAND :{
                        System.out.println("button2 clicked");
                        break;
                    }
                }
            }
        };
        button1.addActionListener(myListener);
        button2.addActionListener(myListener);
        frame.add(panel);
    }
}
