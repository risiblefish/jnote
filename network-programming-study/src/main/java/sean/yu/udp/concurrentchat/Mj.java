package sean.yu.udp.concurrentchat;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import static sean.yu.udp.concurrentchat.ConstantUtil.KOBE_RECEIVED_PORT;
import static sean.yu.udp.concurrentchat.ConstantUtil.MJ_SEND_PORT;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-07 11:55
 **/

public class Mj {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        new Thread(new DemoConcurrentSender(MJ_SEND_PORT,InetAddress.getByName("localhost"),KOBE_RECEIVED_PORT)).start();
        new Thread(new DemoConcurrentReceiver(ConstantUtil.MJ_RECEIVED_PORT)).start();
    }
}
