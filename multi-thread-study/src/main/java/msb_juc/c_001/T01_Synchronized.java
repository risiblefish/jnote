package msb_juc.c_001;

/**
 * @author Sean Yu
 * @date 2021/7/2 9:43
 */
public class T01_Synchronized {
    int count = 10;
    private Object o = new Object();

    public void countDonw(){
        synchronized (o){
            System.out.println("拿到对象锁o, 开始倒数");
            while(count >= 0){
                System.out.println(count--);
            }
        }
    }

    public static void main(String[] args) {
        new T01_Synchronized().countDonw();
    }
}
