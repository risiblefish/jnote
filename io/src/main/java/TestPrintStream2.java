import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author Sean Yu
 */
public class TestPrintStream2 {
    public static void main(String[] args) throws IOException {
        File file = new ClassPathResource("text/test.txt").getFile();
        list(file.getAbsolutePath(), System.out);
    }

    public static void list(String f, PrintStream ps) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s = null;
            while ((s = br.readLine()) != null) {
                ps.println(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

