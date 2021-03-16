package cp.ch17;

/**
 * @author Sean Yu
 */
//设置为包可见
class ReadWriteLockImpl implements ReadWriteLock {

    private final Object MUTEX = new Object();

    private int writingWriters = 0;
    private int waitingWriters = 0;

    private int readingReaders = 0;

    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    public Object getMUTEX() {
        return MUTEX;
    }

    public boolean getPreferWriter() {
        return this.preferWriter;
    }

    void increaseWritingWriters() {
        this.writingWriters++;
    }

    void increaseWaitingWriters() {
        this.waitingWriters++;
    }

    void increaseReadingReaders() {
        this.readingReaders++;
    }

    void decreaseWritingWriters() {
        this.writingWriters--;
    }

    void decreaseWaitingWriters() {
        this.waitingWriters--;
    }

    void decreaseReadingReaders() {
        this.readingReaders--;
    }

    void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
