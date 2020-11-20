import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Sean Yu
 */
public class TestPrintStream1 {
    public static void main(String[] args) {
        PrintStream ps = null;

        try {
            File file = new ClassPathResource("text/test.txt").getFile();
            FileOutputStream fos = new FileOutputStream(file);
            ps = new PrintStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(ps != null) {
            //System.out默认输出是控制台，setOut()方法是设置输出到file这个文件里
            System.setOut(ps);
        }

        int ln = 0;
        for (char c = 0 ; c < 1000; c++) {
            System.out.print(c + " ");
            if(ln++ >= 100) {
                System.out.println();
                ln = 0;
            }
        }
    }
}
