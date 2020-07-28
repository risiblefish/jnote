package sean.yu.swingtest;

import javax.swing.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 07:04
 **/


public class MyBaseJFrame extends JFrame {
    //提供基本的功能：可视化，可关闭，初始位置及大小
    public MyBaseJFrame(){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,400,400);
    }
}
