package sean.yu.udp.msg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-06 22:46
 **/

public class DemoUdpServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
        socket.receive(packet);

        System.out.println(packet.getAddress());
        System.out.println(new String(packet.getData(),0,packet.getLength()));

        socket.close();
    }
}
