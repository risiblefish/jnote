package msb_juc;

import java.util.concurrent.*;

/**
 * @author Sean Yu
 * @date 2021/7/1 8:15
 */
public class T02_HowToCreateThread {
    static int count = 1;
    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println(String.format("method %s : extend Thread",count++));
        }
    }

    static class MyRun implements Runnable{
        @Override
        public void run() {
            System.out.println(String.format("method 2 : impl Runnable",count++));
        }
    }

    static class MyCall implements Callable<String> {
        @Override
        public String call() throws Exception {
            return String.format("method %s : impl Callable<V>",count++);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new MyThread().start();
        new Thread(new MyRun()).start();
        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> f = service.submit(new MyCall());
        String s = f.get();
        System.out.println(s);

        service.execute( () -> {
            System.out.println(String.format("method %s : using ThreadPool",count++));
        });

        service.shutdown();

        FutureTask<String> task = new FutureTask<>(new MyCall());
        Thread t = new Thread(task);
        t.start();
        System.out.println(task.get());
    }


}
