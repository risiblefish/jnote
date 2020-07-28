package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 06:41
 **/

public class JButtonDemo extends JFrame {
    public static void main(String[] args) {
        new JButtonDemo();
    }

    public JButtonDemo() throws HeadlessException {
        JButton button = new JButton();
        ImageIcon imageIcon = new ImageIcon(JButtonDemo.class.getClassLoader().getResource("unbind.jpg"));
        button.setIcon(imageIcon);
        button.setToolTipText("this is a tip");

        getContentPane().add(button);
        setVisible(true);
        setBounds(200,200,300,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
