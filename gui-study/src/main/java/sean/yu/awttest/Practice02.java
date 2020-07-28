package sean.yu.awttest;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 17:48
 **/

public class Practice02 {
    public static void main(String[] args) {
        Frame frame = new Frame();

        Button button1 = new Button("button1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");
        Button button4 = new Button("button4");
        Button button5 = new Button("button5");
        Button button6 = new Button("button6");
        Button button7 = new Button("button7");
        Button button8 = new Button("button8");
        Button button9 = new Button("button9");
        Button button10 = new Button("button10");

        Panel panel1 = new Panel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(button1);
        panel1.add(button2);
        frame.add(panel1, BorderLayout.WEST);

        Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(2, 1));
        panel2.add(button3);
        panel2.add(button4);
        frame.add(panel2, BorderLayout.EAST);

        Panel panel3 = new Panel();
        panel3.setLayout(new GridLayout(4, 1));
        panel3.add(button5);
        panel3.add(button6);

        frame.add(panel3, BorderLayout.CENTER);

        Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(1, 2));
        panel4.add(button7);
        panel4.add(button8);

        Panel panel5 = new Panel();
        panel5.setLayout(new GridLayout(1, 2));
        panel5.add(button9);
        panel5.add(button10);

        panel3.add(panel4);
        panel3.add(panel5);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
