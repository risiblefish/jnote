import java.io.*;

/**
 * @author Sean Yu
 */
public class TestDataStream {
    public static void main(String[] args) {
        //准备输出节点流
        ByteArrayOutputStream baos  = new ByteArrayOutputStream();
        //准备处理流（类型是数据流）来套接输出流
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            //Double类型占8个字节，所以writeDouble会一次性写入8个字节
            dos.writeDouble(Math.random());
            //boolean占1个字节，所以writeBoolean会一次性只写入1个字节
            dos.writeBoolean(true);

            //准备输入节点流
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            //bais中还有多少个字节可供我们读取
            System.out.println(bais.available());
            //准备处理流来套接输入流
            DataInputStream dis = new DataInputStream(bais);
            //需要注意的是，先写先读
            //如果这里换成先读boolean（先读1个字节），再读double（再读8个字节），就会出问题
            System.out.println(dis.readDouble());
            System.out.println(dis.readBoolean());
            dos.close();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
