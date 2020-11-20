import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author Sean Yu
 */
public class TestTransform1 {
    public static void main(String[] args) throws IOException {
        File file = new ClassPathResource("text/test.txt").getFile();
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write("123一二三");
        osw.close();
        fos.close();

        //true表示追加内容，否则是擦除原有内容再写入
        fos = new FileOutputStream(file,true);
        osw = new OutputStreamWriter(fos,"UTF-8");
        osw.write("这是额外追加的内容halo");
        osw.close();
    }
}
