package msb_juc.c_012;

/**
 * 请证明volatile只能让其修饰的引用指向的对象可见，引用指向的对象内部属性的变化仍然不可见
 *
 * @author Sean Yu
 * @date 2021/7/9 11:19
 */
public class VolatileReference {
    boolean flag = true;

    public static void main(String[] args) {
        Test t = new Test();
        Thread t1 = new Thread(() -> {
            int i = 0;
            while(true){
                i++;
                if(i == 10000){
                    t.vr.flag = false;
                }
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            while(!t.vr.flag){
                System.out.println("now t2 found flag is false");
                break;
            }
        },"t2");

        t2.start();
        t1.start();
    }
}

class Test{
    public volatile VolatileReference vr = new VolatileReference();
}
