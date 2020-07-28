package status;

/**
 * 测试线程停止
 * 1.建议让线程正常停止，尽量不使用死循环
 * 2.建议使用标志位
 * 3.不使用stop()或者destroy()等过时的方法
 **/
public class TestStop implements Runnable{
    //设置权限修饰符
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0 ;
        while(flag) {
            System.out.println(String.format("thread run %s " ,i++ ));
        }
    }

    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(String.format("main %s", i));
            if(i == 900) {
                testStop.stop();
                System.out.println("子线程现在停止了");
            }
        }
    }
}
