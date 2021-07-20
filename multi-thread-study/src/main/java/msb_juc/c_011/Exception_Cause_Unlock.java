package msb_juc.c_011;

import msb_juc.util.SleepHelper;

/**
 * @author Sean Yu
 * @date 2021/7/9 10:45
 */
public class Exception_Cause_Unlock {
    /**
     * 验证抛异常会释放锁
     * @param args
     */
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                synchronized (this) {
                    for (; ; i++) {
                        if (i == 5) {
                            i++;
                            //加上try-catch之后，就能不释放锁，并继续执行了
//                            try {
                                System.out.println(1 / 0);
//                            } catch (Exception e) {
//                                System.out.println(String.format("now catch the exception, %s still holds the lock", Thread.currentThread().getName()));
//                            }
                        }
                        System.out.println(String.format("%s holds lock, now i is %s", Thread.currentThread().getName(), i));
                        SleepHelper.sleepSeconds(1);
                    }
                }
            }
        };
        new Thread(r, "t1").start();
        new Thread(r, "t2").start();
    }
}

