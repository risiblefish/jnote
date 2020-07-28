package sean.yu.tcp.tcpfile;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

/**
 * @program: network-programming-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-05 20:06
 **/

public class DemoTcpFileClient {
    public static void main(String[] args) throws IOException {
        //1.使用服务端信息
        InetAddress serverIp = InetAddress.getLocalHost();
        int port = 9998;
        //2.使用该信息创建一个新的连接
        Socket socket  = new Socket(serverIp, port);
        OutputStream os  = socket.getOutputStream();
        //3.读取文件
        URL url = DemoTcpFileClient.class.getClassLoader().getResource("arrow.jpg");
        FileInputStream fis = new FileInputStream(new File(url.getPath()));
        //4.写出文件
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        //5.关闭输出流，这句话必须写
        socket.shutdownOutput();
        System.out.println("file sent");

        //6.等待服务端的确认收到的应答
        //6.1 从会话中获取输入流
        InputStream serverMsgReader = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer2 = new byte[1024];
        int len2;
        while ((len2 = serverMsgReader.read(buffer2)) != -1) {
            baos.write(buffer2, 0, len2);
        }
        System.out.println("msg from server : " + baos.toString());

        //7.按就近使用顺序关闭流
        baos.close();
        serverMsgReader.close();
        fis.close();
        os.close();
        socket.close();
    }
}
