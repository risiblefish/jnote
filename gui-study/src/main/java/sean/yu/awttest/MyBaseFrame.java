package sean.yu.awttest;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 20:21
 **/

public class MyBaseFrame {

    private static final Frame frame;

    /**
     * 使用静态单例模式，返回一个可视的，可关闭的frame
     */
    static{
        frame = new Frame();
        frame.setVisible(true);
        closeWindow(frame);
    }

    public static Frame getInstance(){
        return frame;
    }

    private static void closeWindow(Frame frame){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
