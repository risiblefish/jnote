package sean.yu.awttest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-27 22:21
 **/

public class TestTextField {
    public static void main(String[] args) {
        new MyTextFrame();
    }
}

class MyTextFrame extends Frame {
    public MyTextFrame() throws HeadlessException {
        TextField textField = new TextField();
        this.add(textField);

        ActionListener myTextListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextField field = (TextField) e.getSource();
                System.out.println(field.getText());
                field.setText("");
            }
        };

        textField.addActionListener(myTextListener);
        textField.setEchoChar('*');

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.pack();
    }
}
