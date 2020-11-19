import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sean Yu
 * @date 2020/11/19 21:45
 */
public class TestFileWriter {
    public static void main(String[] args) {
        FileReader fr = null;
        FileWriter fw = null;

        try {
            File file = new ClassPathResource("text/test.txt").getFile();
            File copy = new ClassPathResource("text").getFile();
            fr = new FileReader(file);
            fw = new FileWriter(copy+"/copyW.txt");
            int c = 0;
            while( (c = fr.read() ) != -1) {
                fw.write(c);
            }
            fw.close();
            fr.close();
            System.out.println("文件已复制");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
