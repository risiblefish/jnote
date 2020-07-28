package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 07:22
 **/

public class JCheckBoxDemo extends MyBaseJFrame {
    public static void main(String[] args) {
        new JCheckBoxDemo();
    }

    public JCheckBoxDemo() {
        JCheckBox c1 = new JCheckBox("c1");
        JCheckBox c2 = new JCheckBox("c2");
        JCheckBox c3 = new JCheckBox("c3");
        JCheckBox c4 = new JCheckBox("c4");

        getContentPane().add(c1);
        getContentPane().add(c2);
        getContentPane().add(c3);
        getContentPane().add(c4);

        getContentPane().setLayout(new FlowLayout());
    }
}
