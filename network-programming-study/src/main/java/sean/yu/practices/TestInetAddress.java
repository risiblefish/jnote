package sean.yu.practices;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-05 07:17
 **/

public class TestInetAddress {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost());
        System.out.println(Arrays.toString(InetAddress.getAllByName("www.baidu.com")));
    }
}
