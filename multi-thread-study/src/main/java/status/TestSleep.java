package status;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-07-29 21:22
 **/
//通过sleep来模拟倒计时
public class TestSleep{
    int num = 10;

    public void countDown() throws InterruptedException {
        while(true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if(num <= 0) {
                System.out.println("time over " + num);
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TestSleep().countDown();
    }
}
