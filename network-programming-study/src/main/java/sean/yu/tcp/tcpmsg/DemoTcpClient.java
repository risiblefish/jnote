package sean.yu.tcp.tcpmsg;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-05 20:06
 **/

public class DemoTcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1.使用服务端信息
            InetAddress serverIp = InetAddress.getLocalHost();
            int port = 9999;
            //2.使用该信息创建一个新的连接
            socket = new Socket(serverIp, port);
            //3.发送消息
            os = socket.getOutputStream();
            os.write("hello from sean".getBytes());
            System.out.println("msg sent");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
            socket.close();
        }
    }
}
