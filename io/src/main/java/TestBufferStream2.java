import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author Sean Yu
 * @date 2020/11/19 22:20
 */
public class TestBufferStream2 {
    public static void main(String[] args) {
        try {
            File file = new ClassPathResource("text/test.txt").getFile();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            File copyDir = new ClassPathResource("text").getFile();
            FileWriter fw = new FileWriter(copyDir + "copy_bufferd_writer.txt");
            BufferedWriter bw = new BufferedWriter(fw);

//            int c = 0;
//            while( (c = br.read()) != -1) {
//                bw.write((char)c);
//            }

            String s = null;
            while( (s = br.readLine()) != null) {
                bw.write(s);
                bw.newLine();//如果原样复制，每写完一行，记得用newLine换行
            }
            bw.flush(); //可选，因为while()保证了肯定能够读完
            bw.close();
            br.close();
            System.out.println("文件已复制");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
