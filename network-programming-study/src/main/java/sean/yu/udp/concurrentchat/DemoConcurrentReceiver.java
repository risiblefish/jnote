package sean.yu.udp.concurrentchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static sean.yu.udp.concurrentchat.ConstantUtil.*;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-06 23:19
 **/

public class DemoConcurrentReceiver implements Runnable {

    DatagramSocket socket;

    public DemoConcurrentReceiver(int fromPort) throws SocketException {
        this.socket = new DatagramSocket(fromPort);
    }

    public void run() {
        while(true) {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
                socket.receive(packet);
                String receivedMsg = new String(packet.getData(),0,packet.getLength());
                String msgFrom;
                switch (packet.getPort()) {
                    case KOBE_SEND_PORT : msgFrom = "kobe"; break;
                    case MJ_SEND_PORT : msgFrom = "mj"; break;
                    default: msgFrom = "unknown"; break;
                }
                System.out.println(String.format("msg received from %s : %s", msgFrom,receivedMsg));
                if(receivedMsg.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
