package cp.ch17;

/**
 * @author Sean Yu
 */
public interface Lock {
    //获取显式锁，没有获得锁的线程将被阻塞
    void lock() throws InterruptedException;
    void unlock();
}
