package sean.yu.awttest;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-28 16:57
 **/

public class TestWindowListener {
    public static void main(String[] args) {
        new MyWindowFrame();
    }
}

class MyWindowFrame extends Frame {
    public MyWindowFrame() throws HeadlessException {
        this.setBounds(200,200,600,500);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing");
                System.exit(0);
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("activated");
            }
        });
    }
}
