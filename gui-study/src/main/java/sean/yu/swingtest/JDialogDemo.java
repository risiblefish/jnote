package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-28 21:53
 **/

public class JDialogDemo{
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setBounds(200,200,400,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = jFrame.getContentPane();

        jFrame.setLayout(null);

        JButton button = new JButton("点此可以弹出弹窗");
        button.setBounds(50,50,200,200);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyDialog();
            }
        });

        contentPane.add(button);
    }
}

class MyDialog extends JDialog{
    public MyDialog() {
//        setLayout(null);
        setVisible(true);
        setBounds(700,200,400,400);
        Container contentPane = getContentPane();

        contentPane.setBackground(Color.GREEN);
//        contentPane.setLayout(null);

        JLabel label = new JLabel("hello from sean");
        label.setBackground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
//        label.setBounds(100,100,100,100);

        contentPane.add(label);
    }
}

