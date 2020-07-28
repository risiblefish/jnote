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
 * @create: 2020-06-28 08:23
 **/

public class MyCalculatorTest {
    public static void main(String[] args) {
        new MyCalculator();
    }
}

class MyCalculator extends Frame {
    private final TextField num1Field;
    private final TextField num2Field;
    private final TextField sumField;

    public MyCalculator() throws HeadlessException {

        num1Field = new TextField(10);
        num2Field = new TextField(10);
        sumField = new TextField(11);

        Label operatorPlus = new Label("+");
        Button equalButton = new Button("=");

        equalButton.addActionListener(new MyListener());

        //设置为流式布局并依次加入num1,+,num2,=,sum
        this.setLayout(new FlowLayout());
        this.add(num1Field);
        this.add(operatorPlus);
        this.add(num2Field);
        this.add(equalButton);
        this.add(sumField);

        //设置自适应，可视化，能关闭
        this.pack();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.pack();
    }

    /**
     * 此处使用内部类，可以直接获取外部类的num1Field,num2Field,sumField等属性，很方便，即便这些属性是外部类的私有属性
     */
    private class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            long num1 = Long.parseLong(num1Field.getText());
            long num2 = Long.parseLong(num2Field.getText());
            sumField.setText(num1 + num2 + "");
            num1Field.setText("");
            num2Field.setText("");
        }
    }
}


