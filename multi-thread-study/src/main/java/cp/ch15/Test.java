package cp.ch15;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class Test {
    public static void main(String[] args) {
        testStringResultObserverThread();
    }

    static void testVoidObserverThread() {
        Observable obvervableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done");
            return null;
        });
        obvervableThread.start();
    }


    static void testStringResultObserverThread() {
        final TaskLifeCycle<String> lifeCycle = new TaskLifeCycle.EmptyLifeCycle<String>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println(String.format("The result is [%s]", result));
            }
        };

        Observable observableThread = new ObservableThread<>(lifeCycle, () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done.");
            return "Hello Observer";
        });

        observableThread.start();
    }
}
