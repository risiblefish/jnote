package sean.yu.swingtest;

import javax.swing.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-01 08:07
 **/

public class JListDemo extends MyBaseJFrame {
    public static void main(String[] args) {
        new JListDemo();
    }

    public JListDemo() {
        JList jList = new JList(new String[]{"1","2","3"});
        getContentPane().add(jList);
    }
}
