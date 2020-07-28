package sean.yu.udp.msg;

import java.io.IOException;
import java.net.*;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-06 22:42
 **/

public class DemoUdpClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        byte[] content = "hello from sean".getBytes();
        DatagramPacket packet
                = new DatagramPacket(content, 0, content.length, InetAddress.getLocalHost(), 9090);

        socket.send(packet);
    }
}
