package cp.ch15;

/**
 * @author Sean Yu
 */
public class ObservableThread<T> extends Thread implements Observable {
    private final TaskLifeCycle<T> lifeCycle;

    private final Task<T> task;

    private Observable.Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new TaskLifeCycle.EmptyLifeCycle<>(), task);
    }

    public ObservableThread(TaskLifeCycle<T> lifeCycle, Task<T> task) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("the Task cannot be empty");
        }
        this.lifeCycle = lifeCycle;
        this.task = task;
    }

    @Override
    public final void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }


    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifeCycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifeCycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifeCycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if (cycle == null) {
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
