package sean.yu.awttest;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-28 10:24
 **/

public class TestPaint {
    public static void main(String[] args) {
        new MyPaint();
    }
}

class MyPaint extends Frame {
    public MyPaint() throws HeadlessException {
        this.setBounds(200,200,600,500);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g){
        g.drawOval(100,100,100,100);
        g.fillRect(150,200,200,200);
    }
}
