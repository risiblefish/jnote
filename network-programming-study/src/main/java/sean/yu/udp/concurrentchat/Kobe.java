package sean.yu.udp.concurrentchat;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import static sean.yu.udp.concurrentchat.ConstantUtil.*;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-07 11:54
 **/

public class Kobe {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        new Thread(new DemoConcurrentSender(KOBE_SEND_PORT,InetAddress.getByName("localhost"),MJ_RECEIVED_PORT)).start();
        new Thread(new DemoConcurrentReceiver(ConstantUtil.KOBE_RECEIVED_PORT)).start();
    }
}
