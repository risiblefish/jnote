package sean.yu.awttest;


import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-26 21:54
 **/

public class TestFrame {
    public static void main(String[] args) {
        Frame frame = new Frame("my 1st gui");

        //设置可见性
        frame.setVisible(true);

        //设置大小
        frame.setSize(400,400);

        //设置背景颜色
        frame.setBackground(Color.GREEN);

        //设置弹出时的初始位置，左上角为00
        frame.setLocation(500,200);

        //设置弹窗大小不可更改
        frame.setResizable(false);
    }
}
