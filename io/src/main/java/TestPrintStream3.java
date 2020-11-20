import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author Sean Yu
 */
public class TestPrintStream3 {
    public static void main(String[] args) {
        String s = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            File file = new ClassPathResource("text/test.txt").getFile();
            FileWriter fw = new FileWriter(file);
            PrintWriter log = new PrintWriter(fw);
            while( (s = br.readLine() ) != null) {
                if(s.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println(s.toUpperCase());
                log.println("-------");
                log.println(s.toUpperCase());
                log.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
