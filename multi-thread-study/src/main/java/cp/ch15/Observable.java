package cp.ch15;

/**
 * @author Sean Yu
 */
public interface Observable {

    enum Cycle{
        STARTED, RUNNING, DONE, ERROR
    }

    Cycle getCycle();
    void start();
    void interrupt();
}
