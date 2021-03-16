package cp.ch08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不再继承Thread，而是将Thread作为属性
 *
 * @author Sean Yu
 */
public class BasicThreadPool2 implements MyThreadPool {

    private final Thread poolThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!isShutdown && !poolThread.isInterrupted()) {
                try {
                    timeUnit.sleep(keepAliveTime);
                } catch (InterruptedException e) {
                    isShutdown = true;
                    break;
                }
                synchronized (this) {
                    if (isShutdown) {
                        break;
                    }

                    if (runnableQueue.size() > 0 && activeCount < coreSize) {
                        for (int i = initSize; i < coreSize; i++) {
                            newThread();
                        }
                        continue;
                    }

                    if (runnableQueue.size() > 0 && activeCount < maxSize) {
                        for (int i = coreSize; i < maxSize; i++) {
                            newThread();
                        }
                    }

                    if (runnableQueue.size() == 0 && activeCount > coreSize) {
                        for (int i = coreSize; i < activeCount; i++) {
                            removeThread();
                        }
                    }
                }
            }
        }
    });

    private final int initSize;

    private final int maxSize;

    private final int coreSize;

    private int activeCount;

    private final MyThreadFactory threadFactory;

    //任务队列
    private final RunnableQueue runnableQueue;

    private volatile boolean isShutdown = false;

    //工作线程队列
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
    private final static MyThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;
    private final TimeUnit timeUnit;

    public BasicThreadPool2(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, queueSize, DEFAULT_THREAD_FACTORY, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool2(int initSize, int maxSize, int coreSize, int queueSize, MyThreadFactory threadFactory,
                            DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    //初始化时，先创建initSize个线程
    private void init() {
        //异步执行run方法，与this.run()的区别在于，start()会调用jni的start0来执行run，start0执行时机由jvm控制
        poolThread.start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    /**
     * 这个方法会在线程池init和扩容时被调用，创建出来的这些线程的线程体作用就是从任务队列里取出任务然后执行
     */
    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown) {
            throw new IllegalArgumentException("the thread pool is destroy");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            poolThread.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if (this.isShutdown) {
            throw new IllegalArgumentException("the thread pool is destroy");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (this.isShutdown) {
            throw new IllegalArgumentException("the thread pool is destroy");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (this.isShutdown) {
            throw new IllegalArgumentException("the thread pool is destroy");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (this.isShutdown) {
            throw new IllegalArgumentException("the thread pool is destroy");
        }
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask task) {
            this.thread = thread;
            this.internalTask = task;
        }
    }

    private static class DefaultThreadFactory implements MyThreadFactory {
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup group = new ThreadGroup(String.format("MyThreadPool-%s", GROUP_COUNTER.getAndDecrement()));
        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "my-thread-pool-" + COUNTER.getAndDecrement());
        }
    }
}
