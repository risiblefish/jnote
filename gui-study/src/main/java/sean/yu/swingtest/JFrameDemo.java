package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-28 21:13
 **/

public class JFrameDemo {

    private JFrame jFrame;

    public void init() {
        jFrame = new JFrame("it's a jframe");
        jFrame.setVisible(true);
        jFrame.setBounds(100,100,200,200);

        // jFrame.setBackground(Color.GREEN); //注意，这样不会生效
        jFrame.getContentPane().setBackground(Color.GREEN); //这样才会生效

        JLabel label = new JLabel("hello from sean");
        label.setHorizontalAlignment(JLabel.CENTER);

//        jFrame.add(label);//不推荐
        jFrame.getContentPane().add(label);//推荐

        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JFrameDemo().init();
    }
}

