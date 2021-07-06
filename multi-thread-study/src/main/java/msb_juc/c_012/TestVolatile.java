package msb_juc.c_012;

import msb_juc.util.SleepHelper;

/**
 * @author Sean Yu
 * @date 2021/7/2 19:47
 */
public class TestVolatile {

    //分别对比running使用/不使用 volatile的效果
    boolean running = true;

    public void m(){
        System.out.println("m start");
        while(running){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        TestVolatile test = new TestVolatile();
        new Thread(test::m).start();
        SleepHelper.sleepSeconds(1);
        test.running = false;
    }
}
