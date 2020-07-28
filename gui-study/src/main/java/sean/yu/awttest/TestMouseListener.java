package sean.yu.awttest;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-28 11:50
 **/

public class TestMouseListener {
    public static void main(String[] args) {
        new MyFrame();
    }
}

class MyFrame extends Frame {
    private static final ArrayList<Point> points = new ArrayList();

    public MyFrame() throws HeadlessException {
        this.setBounds(200,200,600,500);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addMouseListener(new MyMouseListener());
    }

    private class MyMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            Point point = new Point(e.getX(),e.getY());
            points.add(point);
            //内部类获取外部类的对象 -> <外部类名>.this
//            MyFrame.this.repaint();
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        Iterator pointsIterator = points.iterator();
        while(pointsIterator.hasNext()) {
            Point curr = (Point) pointsIterator.next();
            g.fillOval(curr.x,curr.y,10,10);
        }
    }
}
