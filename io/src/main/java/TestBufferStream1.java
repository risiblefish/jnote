import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Sean Yu
 * @date 2020/11/19 22:08
 */
public class TestBufferStream1 {
    public static void main(String[] args) {
        try {
            File file = new ClassPathResource("text/test.txt").getFile();
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            int c = 0;
            System.out.println((char)bis.read());
            System.out.println((char)bis.read());
            while( (c = bis.read()) != -1) {
                System.out.print((char)c);
            }
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream fis = null;

    }
}
