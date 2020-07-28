package sean.yu.awttest;

import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 16:11
 **/

public class TestBorderLayout {
    public static void main(String[] args) {
        Frame frame = new Frame("testBorderLayout");

        Button north = new Button("north");
        Button south = new Button("south");
        Button east = new Button("east");
        Button west = new Button("west");
        Button center = new Button("center");

        frame.add(east,BorderLayout.EAST);
        frame.add(west,BorderLayout.WEST);
        frame.add(south,BorderLayout.SOUTH);
        frame.add(north,BorderLayout.NORTH);
        frame.add(center,BorderLayout.CENTER);

        frame.setSize(200,200);
        frame.setVisible(true);
    }
}
