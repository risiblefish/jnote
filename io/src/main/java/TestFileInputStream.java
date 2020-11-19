import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * 用这个版本，可以看到，中文读出来是乱码
 * 原因是因为read()方法是1个字节1个字节的读取，而每个中文字符占2个字节
 *
 * @author Sean Yu
 */
public class TestFileInputStream {
    public static void main(String[] args) throws IOException {
        File file = new ClassPathResource("text/test.txt").getFile();
        readOneBytePerTime(file);
    }

    /**
     * 每次读1个字节，遇到中文会乱码，因为中文占2个字节
     * 要接决这个方法，不能用字节流来处理，而改用字符流Reader就可以了
     * @param file
     */
    static void readOneBytePerTime(File file){
        int b = 0;
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            long num = 0; //仅仅是计数使用
            while ((b = in.read()) != -1) { // 这里为什么要先把in.read()先赋值给b? -> 因为b后面会用到，如果不用，则不用赋值
                System.out.println((char) b);
                num++;
            }
            in.close();
            System.out.println();
            System.out.println(String.format("共读取了%s个字节", num));
        } catch (IOException e) {
            System.out.println("文件读取错误");
            System.exit(-1);
        }
    }
}

