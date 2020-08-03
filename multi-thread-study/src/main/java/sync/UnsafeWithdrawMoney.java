package sync;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 17:05
 **/

public class UnsafeWithdrawMoney {
    public static void main(String[] args) {
        Account account = new Account(300, "张三");
        WithDraw wd1 = new WithDraw(account, 10);
        WithDraw wd2 = new WithDraw(account, 20);
        WithDraw wd3 = new WithDraw(account, 30);
        Thread t1 = new Thread(wd1, "张三");
        Thread t2 = new Thread(wd2, "李四");
        Thread t3 = new Thread(wd3, "王五");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Account{
    int balance;
    String name;

    public Account(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }
}

class WithDraw implements Runnable{
    private Account account;
    int cost;

    public WithDraw(Account account,int cost) {
        this.account = account;
        this.cost = cost;
    }

    @Override
    public void run() {
        int sum = 0;
        String operator = Thread.currentThread().getName();
        while (account.balance - cost >= 0) {
            account.balance -= cost;
            sum += cost;
            System.out.println(String.format("%s 从账户账户[%s] 取了 %s 元， 此时账户内余额 %s 元",
                   operator , account.name,cost, account.balance));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(operator + "本次总共取了 " + sum + "元");
    }
}
