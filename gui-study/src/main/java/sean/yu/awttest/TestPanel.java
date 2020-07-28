package sean.yu.awttest;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-26 23:33
 **/

// panel(面板)可以看成一个空间，但是不能单独存在
public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame();
        Panel panel = new Panel();

        frame.setLayout(null);

        frame.setBounds(300,300,500,500);
        frame.setBackground(Color.GREEN);

        //相对于frame
        panel.setBounds(50,0,400,400);
        panel.setBackground(Color.RED);

        frame.add(panel);
        frame.setVisible(true);

        //设置监听事件，点击窗口退出应用
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
