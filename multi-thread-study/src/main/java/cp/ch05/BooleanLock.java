package cp.ch05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 这是Lock的一种实现，通过控制一个boolean变量的开关来决定是否允许当前的线程获取该锁
 * 它和synchronized功能类似，增强的地方是 可中断 和 可设置超时
 *
 * @author Sean Yu
 */
public class BooleanLock implements Lock {

    //当前的线程
    private Thread currentThread;
    //当前是否加锁
    private boolean locked = false;
    //阻塞列表 ： 存储当前阻塞的线程
    private final List<Thread> blockList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                //暂存加锁的线程，目的是在捕获中断异常时，将其从阻塞列表里移除
                final Thread tempThread = Thread.currentThread();
                try {
                    if (!blockList.contains(tempThread)) {
                        blockList.add(tempThread);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    //如果当前线程在wait时被中断，则从blockList中将其删除，避免内存泄漏
                    blockList.remove(tempThread);
                    //继续抛出中断异常
                    throw e;
                }
            }
            blockList.remove(Thread.currentThread());
            this.locked = true;
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("cannot get the lock during " + mills);
                    }
                    //暂存加锁的线程，目的是在捕获中断异常时，将其从阻塞列表里移除
                    final Thread tempThread = Thread.currentThread();
                    try {
                        if (!blockList.contains(tempThread)) {
                            blockList.add(tempThread);
                        }
                        this.wait(remainingMills);
                        remainingMills = endMills - System.currentTimeMillis();
                    } catch (InterruptedException e) {
                        //如果当前线程在wait时被中断，则从blockList中将其删除，避免内存泄漏
                        blockList.remove(tempThread);
                        //继续抛出中断异常
                        throw e;
                    }
                }
                blockList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == Thread.currentThread()) {
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockList);
    }
}
