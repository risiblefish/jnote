package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-30 22:09
 **/

public class JPanelDemo extends JFrame {
    public static void main(String[] args) {
        new JPanelDemo();
    }

    public JPanelDemo() throws HeadlessException {
        Container container = getContentPane();
        container.setLayout(new GridLayout(2,1,10,10));//后面10，10是指的行列间距

        JPanel panel1 = new JPanel(new GridLayout(1,3));
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        JPanel panel3 = new JPanel(new GridLayout(2,1));
        JPanel panel4 = new JPanel(new GridLayout(3,2));

        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));

        panel2.add(new JButton("2"));
        panel2.add(new JButton("2"));

        panel3.add(new JButton("3"));
        panel3.add(new JButton("3"));

        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));

        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);

        setSize(600,600);
        setLocation(200,200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
