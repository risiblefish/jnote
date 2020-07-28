package sean.yu.simplesnake;

import javax.swing.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 22:56
 **/

public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10,10,900,720);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new GamePanel());
    }
}
