package sean.yu.tcp.tcpmsg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-05 20:06
 **/

public class DemoTcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //注册服务器地址
            serverSocket = new ServerSocket(9999);
            //等待客户端连接
            socket = serverSocket.accept();//这是一个阻塞式的方法，接受一次后关闭
            //读取客户端消息
            is = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            System.out.println("msg received : " + baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            baos.close();
            ;
            is.close();
            socket.close();
        }
    }
}
