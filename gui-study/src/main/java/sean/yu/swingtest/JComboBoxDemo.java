package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 07:39
 **/

public class JComboBoxDemo extends MyBaseJFrame {
    public static void main(String[] args) {
        new JComboBoxDemo();
    }

    public JComboBoxDemo() {
        JComboBox status = new JComboBox();
        status.addItem("");
        status.addItem("正在热映");
        status.addItem("即将上映");
        status.addItem("已下架");

        getContentPane().add(status,BorderLayout.CENTER);

        ActionListener myListener = e -> {
            JComboBox status1 = (JComboBox) e.getSource();
            switch ((String) status1.getSelectedItem()){
                case "" : System.out.println(""); break;
                case "正在热映" : System.out.println("正在热映"); break;
                case "即将上映" : System.out.println("即将上映"); break;
                case "已下架" : System.out.println("已下架"); break;
            }
        };
        status.addActionListener(myListener);
    }
}
