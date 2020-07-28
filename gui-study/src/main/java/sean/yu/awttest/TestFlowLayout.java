package sean.yu.awttest;

import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 16:11
 **/

public class TestFlowLayout {
    public static void main(String[] args) {
        Frame frame = new Frame("testFlowLayout");

        Button button = new Button("button1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");

        frame.setLayout(new FlowLayout());
        frame.setSize(200,200);
        frame.add(button);
        frame.add(button2);
        frame.add(button3);
        frame.setVisible(true);
    }
}
