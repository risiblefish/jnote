package sean.yu.awttest;

import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 16:31
 **/

public class TestGridLayout {
    public static void main(String[] args) {
        Frame frame = new Frame();

        Button button1 = new Button("button1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");
        Button button4 = new Button("button4");
        Button button5 = new Button("button5");
        Button button6 = new Button("button6");

        frame.setLayout(new GridLayout(3,2));

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);

        frame.setVisible(true);
        frame.pack();//自动为frame设置大小
    }
}
