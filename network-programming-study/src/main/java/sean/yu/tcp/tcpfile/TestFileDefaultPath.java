package sean.yu.tcp.tcpfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-05 21:05
 **/

public class TestFileDefaultPath {
    public static void main(String[] args) throws FileNotFoundException {
//        FileInputStream fis = new FileInputStream(new File("resources/arrow.jpg"));
        URL url = TestFileDefaultPath.class.getClassLoader().getResource("arrow.jpg");
        FileInputStream fis = new FileInputStream(new File(url.getPath()));

        System.out.println(url);
        System.out.println(url.getPath());
        new FileInputStream(new File(url.getPath()));

//        System.out.println(new File(filePath).getAbsolutePath());

    }
}
