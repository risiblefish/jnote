package sean.yu.udp.singlechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-06 23:19
 **/

public class DemoSender {
    public static void main(String[] args) throws IOException {

        final int ANOTHER_CHATTER_PORT = 6666;

        DatagramSocket socket = new DatagramSocket(7777);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String content = reader.readLine();
            if(content.equals("bye")) {
                break;
            }
            byte[] contentByte = content.getBytes();
            DatagramPacket packet = new DatagramPacket(contentByte, 0, contentByte.length, InetAddress.getLocalHost(), ANOTHER_CHATTER_PORT);
            socket.send(packet);
        }
        reader.close();
        socket.close();
    }
}
