package sean.yu.tcp.tcpfile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-05 20:06
 **/
public class DemoTcpFileServer {
    public static void main(String[] args) throws IOException {
        //注册服务器地址
        ServerSocket serverSocket = new ServerSocket(9998);
        //等待客户端连接
        Socket socket = serverSocket.accept();//这是一个阻塞式的方法，接受一次后关闭
        System.out.println("connection established");
        //读取客户端消息
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File("copy.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        //告知客户端，文件已收到
        //获取会话的输出流
        OutputStream os = socket.getOutputStream();
        os.write("file recived".getBytes());
        //关闭流
        os.close();
        fos.close();
        is.close();
        socket.close();
    }
}
