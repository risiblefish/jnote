package cp.ch05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author Sean Yu
 */
public interface Lock {
    /**
     * 在获取到锁之前将持续阻塞（和synchronized类似）
     * 但该方法可以被中断。
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 在lock()基础上，增加了超时功能
     * @param mills
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * 对锁进行释放
     */
    void unlock();

    /**
     * 获取当前被阻塞的线程
     * @return
     */
    List<Thread> getBlockedThreads();
}
