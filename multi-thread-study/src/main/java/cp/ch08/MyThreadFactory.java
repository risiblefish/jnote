package cp.ch08;

/**
 * @author Sean Yu
 */
@FunctionalInterface
public interface MyThreadFactory {
    Thread createThread(Runnable runnable);
}
