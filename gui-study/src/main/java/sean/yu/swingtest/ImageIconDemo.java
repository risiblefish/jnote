package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-30 07:52
 **/

public class ImageIconDemo extends JFrame {

    public static void main(String[] args) {
        new ImageIconDemo();
    }

    public ImageIconDemo() throws HeadlessException {
        //首先获取图片地址
        URL url = this.getClass().getClassLoader().getResource("ARROW.jpg");

//        new JLabel("imageIcon",new ImageIcon(url),SwingConstants.CENTER);
        JLabel label = new JLabel("imageIcon");
        label.setIcon(new ImageIcon(url));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        getContentPane().add(label);
        setBounds(100, 100, 200, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
