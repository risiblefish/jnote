import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author Sean Yu
 * @date 2020/11/19 21:37
 */
public class TestFileReader {
    public static void main(String[] args) {
        FileReader fr = null;
        int c = 0;
        try {
            fr = new FileReader(new ClassPathResource("text/test.txt").getFile());
            while( (c = fr.read()) != -1) {
                System.out.println((char)c);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
