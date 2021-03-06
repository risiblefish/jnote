import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author Sean Yu
 */
public class TestFileOutputStream {
    public static void main(String[] args) {
        int b = 0;
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            File file = new ClassPathResource("text/test.txt").getFile();
            in = new FileInputStream(file);
            File outPutFileDir = new ClassPathResource("text").getFile();
//            if(!outPutFileDir.exists()) {
//                outPutFileDir.mkdir();
//            }
            File copy = new File(outPutFileDir + "/copy.txt");
            out = new FileOutputStream(copy);
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
            System.out.println("文件已复制");
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件复制错误");
            e.printStackTrace();
        }
    }
}
