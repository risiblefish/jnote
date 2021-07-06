package msb_juc.c_008;

import lombok.Data;
import msb_juc.util.SleepHelper;

/**
 * 模拟一个账户，对写加锁，对读不加锁，这样是否合理？
 *
 * @author Sean Yu
 * @date 2021/7/2 17:33
 */
@Data
public class Account {
    private String name;
    private int balance;

    public Account(String name) {
        this.name = name;
    }

    public void setBalance(int num){
        System.out.println(String.format("开始设置%s的余额",name));
        synchronized (this){
            SleepHelper.sleepSeconds(3);
            this.balance = num;
        }
        System.out.println("设置结束");
    }

    public int getBalance(){
        System.out.println("读到的余额是："+ balance);
        return balance;
    }

    public static void main(String[] args) {
        Account ac = new Account("sean");

        new Thread(() -> {
            ac.setBalance(10);
        }).start();

        new Thread(ac::getBalance).start();

    }

}
