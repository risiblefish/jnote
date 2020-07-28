package sean.yu.awttest;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 16:52
 **/

public class Practice01 {
    public static void main(String[] args) {
        Frame frame = new Frame("practice");

        frame.setLayout(null);
        frame.setBounds(300,300,600,600);

        Panel panel1 = new Panel();
        Panel panel2 = new Panel();
        Panel panel3 = new Panel();
        Panel panel4 = new Panel();

        panel1.setBounds(0,0,100,300);
        panel2.setBounds(0,300,100,300);
        panel3.setBounds(500,0,100,300);
        panel4.setBounds(500,300,100,300);

        panel1.setBackground(Color.RED);
        panel2.setBackground(Color.BLUE);
        panel3.setBackground(Color.RED);
        panel4.setBackground(Color.BLUE);

        Panel panel5 = new Panel();
        panel5.setBounds(100,0,400,150);
        panel5.setBackground(Color.ORANGE);

        Panel panel6 = new Panel();
        panel6.setBounds(100,150,400,150);
        panel6.setBackground(Color.MAGENTA);

        Panel panel7 = new Panel();
        panel7.setBounds(100,300,200,150);
        panel7.setBackground(Color.BLACK);

        Panel panel8 = new Panel();
        panel8.setBounds(100,450,200,150);
        panel8.setBackground(Color.GREEN);

        Panel panel9 = new Panel();
        panel9.setBounds(300,300,200,150);
        panel9.setBackground(Color.ORANGE);

        Panel panel10 = new Panel();
        panel10.setBounds(300,450,200,150);
        panel10.setBackground(Color.RED);

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.add(panel6);
        frame.add(panel7);
        frame.add(panel8);
        frame.add(panel9);
        frame.add(panel10);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
