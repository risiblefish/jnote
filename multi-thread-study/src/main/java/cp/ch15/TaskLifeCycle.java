package cp.ch15;

/**
 * @author Sean Yu
 */
public interface TaskLifeCycle<T> {
    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread, Exception e);

    class EmptyLifeCycle<T> implements TaskLifeCycle<T>{

        @Override
        public void onStart(Thread thread) {
            System.out.println("now the thred is start");
        }

        @Override
        public void onRunning(Thread thread) {
            System.out.println("now the thread is running");
        }

        @Override
        public void onFinish(Thread thread, T result) {
        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
