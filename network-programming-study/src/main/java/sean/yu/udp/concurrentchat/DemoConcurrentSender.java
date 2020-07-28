package sean.yu.udp.concurrentchat;

import java.io.BufferedReader;
import java.io.IOException;
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

public class DemoConcurrentSender implements Runnable {
    DatagramSocket socket;
    BufferedReader reader;
    InetAddress toIp;
    int toPort;

    public DemoConcurrentSender(int fromPort, InetAddress toIp, int toPort)
            throws SocketException {
        this.socket = new DatagramSocket(fromPort);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.toIp = toIp;
        this.toPort = toPort;
    }

    public void run() {
        try {
            while (true) {
                String content = null;
                content = reader.readLine();
                byte[] contentByte = content.getBytes();
                DatagramPacket packet = new DatagramPacket(contentByte, 0, contentByte.length, this.toIp, this.toPort);
                socket.send(packet);
                if (content.equals("bye")) {
                    break;
                }
            }
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
