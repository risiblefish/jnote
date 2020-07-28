package sean.yu.udp.concurrentchat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-07 12:14
 **/

public class Test {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(Arrays.toString(InetAddress.getLocalHost().getAddress()));
        System.out.println(InetAddress.getByName("localhost"));
    }
}
