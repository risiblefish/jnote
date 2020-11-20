import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Sean Yu
 */
public class TestTransform2 {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = null;

        try {
            while( (s = br.readLine()) != null ) {
                if("exit".equalsIgnoreCase(s)) {
                    break;
                }
                System.out.println(s.toUpperCase());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
