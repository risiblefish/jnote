package cp.ch17;

/**
 * @author Sean Yu
 */
public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        //使用MUTEX作为锁
        synchronized (readWriteLock.getMUTEX()) {
            //情况1 ： 如果此时有线程正在进行写操作
            while (readWriteLock.getWritingWriters() > 0
                    //情况2 ： 有写线程在等待 并 且偏向写的标识为true时
                    //当满足任一种情况时，读线程就会无法获得锁，只能被挂起
                    || (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                System.out.println(String.format("Reader Observed : Writing writers[%s], preferWriter[%s], Waiting writers[%s]",
                        readWriteLock.getWaitingWriters(), readWriteLock.getPreferWriter(), readWriteLock.getWaitingWriters()));
                readWriteLock.getMUTEX().wait();
            }
            //成功获得锁，使在读的reader数量增加
            readWriteLock.increaseReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            //释放锁的过程，就是使得当前reading的数量-1
            readWriteLock.decreaseReadingReaders();
            //将preferWriter置为true，可以使得writer线程获得更多的机会
            readWriteLock.changePrefer(true);
            //通知唤醒与MUTEX关联monitor waitset中的线程
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
