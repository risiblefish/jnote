package cp.ch04;

/**
 * @author Sean Yu
 */
public class TestWait {
    private void testWait(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testNotify(){
        this.notify();
    }

    /**
     * 无论执行哪个方法，都会抛出异常，因为wait和notify只能在同步方法中使用。
     * 这是因为只有在获得thisMonitor锁的前提下，才能使用这2个方法。
     * @param args
     */
    public static void main(String[] args) {
        TestWait test = new TestWait();
        test.testWait();
        test.testNotify();
    }
}
