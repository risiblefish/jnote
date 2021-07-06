package msb_juc.c_020;

import msb_juc.util.SleepHelper;

import java.util.concurrent.Semaphore;

public class T11_TestSemaphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2,true);
//        Semaphore s = new Semaphore(2);
        Runnable r = () -> {
            try{
                s.acquire();
                System.out.println(String.format("car %s passed",Thread.currentThread().getName()));
                SleepHelper.sleepSeconds(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                s.release();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(r,"no." + (i+1)).start();
        }
    }
}
