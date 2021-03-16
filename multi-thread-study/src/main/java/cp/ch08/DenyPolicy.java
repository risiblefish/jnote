package cp.ch08;

/**
 * @author Sean Yu
 */
@FunctionalInterface
public interface DenyPolicy {
    void reject(Runnable runnable, MyThreadPool threadPool);

    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, MyThreadPool threadPool) {
            //do nothing
        }
    }

    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, MyThreadPool threadPool) {
            throw new RunnableDenyException(String.format("The runnable %s will be abort", runnable));
        }
    }

    //该拒绝策略会使任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, MyThreadPool threadPool) {
            if(!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
