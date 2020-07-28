package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 07:03
 **/

public class JRadioButtonDemo extends MyBaseJFrame {
    public static void main(String[] args) {
        new JRadioButtonDemo();
    }

    public JRadioButtonDemo() {
        /**
         * radiobutton 和 button的区别：
         * 前者是单选按钮，要和buttonGroup同用，在一个group里能有1个button被选中。
         * 后者只是普通按钮。
         */

        JRadioButton radioButton1 = new JRadioButton("b1");
        JRadioButton radioButton2 = new JRadioButton("b2");
        JRadioButton radioButton3 = new JRadioButton("b3");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        getContentPane().add(radioButton1, BorderLayout.NORTH);
        getContentPane().add(radioButton2, BorderLayout.CENTER);
        getContentPane().add(radioButton3, BorderLayout.SOUTH);
    }
}
