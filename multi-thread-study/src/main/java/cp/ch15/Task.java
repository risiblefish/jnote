package cp.ch15;

/**
 * @author Sean Yu
 */
@FunctionalInterface
public interface Task<T> {
    T call();
}
