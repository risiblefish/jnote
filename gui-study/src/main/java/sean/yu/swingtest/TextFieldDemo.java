package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 22:30
 **/

public class TextFieldDemo extends MyBaseJFrame{
    public static void main(String[] args) {
        new TextFieldDemo();
    }

    public TextFieldDemo() {
        JTextField field1 = new JTextField();
        JPasswordField field2 = new JPasswordField();

        JPanel panel = new JPanel();
        panel.add(field1);
        panel.add(field2);
        panel.setLayout(new GridLayout(2,1));
        getContentPane().add(panel);
    }
}
