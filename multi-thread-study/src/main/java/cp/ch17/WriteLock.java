package cp.ch17;

/**
 * @author Sean Yu
 */
public class WriteLock implements Lock {
    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                //等待写入的计数器数量+1
                readWriteLock.increaseWaitingWriters();
                //条件1：如果当前有其他线程正在读
                while (readWriteLock.getReadingReaders() > 0
                        //条件2： 如果当前有其他线程正在写
                        || readWriteLock.getWritingWriters() > 0) {
                    System.out.println(String.format("Writer Observed : Reading readers[%s], Writing writers[%s]",
                            readWriteLock.getReadingReaders(), readWriteLock.getWritingWriters()));
                    //满足任一条件，该写线程将被挂起
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                //成功获取到写入锁，将等待写入的计数器-1
                this.readWriteLock.decreaseWaitingWriters();
            }
            //将正在写入的计数器+1
            readWriteLock.increaseWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            readWriteLock.decreaseWritingWriters();
            readWriteLock.changePrefer(false);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
