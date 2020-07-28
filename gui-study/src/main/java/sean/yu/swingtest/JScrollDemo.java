package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-30 22:18
 **/
public class JScrollDemo extends JFrame {
    public static void main(String[] args) {
        new JScrollDemo();
    }

    public JScrollDemo() throws HeadlessException {
        Container container = getContentPane();
        TextArea textArea = new TextArea(20,50);
        textArea.setText("hello from sean");

        JScrollPane jScrollPane = new JScrollPane();
        container.add(jScrollPane);
        container.add(textArea);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,600,600);
    }
}
