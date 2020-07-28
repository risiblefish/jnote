package sean.yu.udp.singlechat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-06 23:19
 **/

public class DemoReceiver {
    public static void main(String[] args) throws IOException {
        final int ANOTHER_CHATTER_PORT = 7777;
        DatagramSocket socket = new DatagramSocket(6666);

        while(true) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
            socket.receive(packet);

            String receivedMsg = new String(packet.getData(),0,packet.getLength());
            System.out.println("msg received : " + receivedMsg );

            if(receivedMsg.equals("bye")){
                break;
            }
        }
        socket.close();
    }
}
