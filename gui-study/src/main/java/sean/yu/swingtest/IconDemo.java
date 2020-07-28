package sean.yu.swingtest;

import javax.swing.*;
import java.awt.*;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-30 07:32
 **/

public class IconDemo extends JFrame implements Icon {
    private int height;
    private int width;

    public static void main(String[] args) {
        new IconDemo(150,150);
    }

    public IconDemo(int height, int width) {
        this.height = height;
        this.width = width;
        JLabel label = new JLabel("icon", this, SwingConstants.CENTER);
        this.add(label);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.fillOval(x, y, width, height);
    }

    @Override
    public int getIconWidth() {
        return height;
    }

    @Override
    public int getIconHeight() {
        return width;
    }
}
