package sean.yu.url;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-07 17:00
 **/

public class URLdownload {
    public static void main(String[] args) throws IOException {
        String downloadSource = "https://ws.stream.qqmusic.qq.com/C400003xuYjZ1IIbtO.m4a?guid=2210128444&vkey=8242D550CB05C87C6A6A5431766D814E6C67C1A4B743F18F0C4A7DC7096853CD4465ED064079AE757968D300B523BF89DDE0A7CD3B5E0DE6&uin=0&fromtag=66";
        URL url = new URL(downloadSource);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream is = connection.getInputStream();

        String outputSource = "output.m4a";
        FileOutputStream fos = new FileOutputStream(outputSource);

        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1) {
            fos.write(buffer,0 ,len);
        }
    }
}
