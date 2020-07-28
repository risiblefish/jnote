package sean.yu.simplesnake;

import javax.swing.*;
import java.net.URL;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-04 08:42
 **/

//data center
public class Data {
//    private static URL headerUrl = Data.class.getClassLoader().getResource("statics"); //均可
    private static URL headerUrl = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);

    private static URL bodyUrl = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);

    private static URL upUrl = Data.class.getResource("/statics/up.png");
    public static ImageIcon up = new ImageIcon(upUrl);

    private static URL downUrl = Data.class.getResource("/statics/down.png");
    public static ImageIcon down = new ImageIcon(downUrl);

    private static URL leftUrl = Data.class.getResource("/statics/left.png");
    public static ImageIcon left = new ImageIcon(leftUrl);

    private static URL rightUrl = Data.class.getResource("/statics/right.png");
    public static ImageIcon right = new ImageIcon(rightUrl);

    private static URL foodUrl = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);


    public static void main(String[] args) {

    }
}
