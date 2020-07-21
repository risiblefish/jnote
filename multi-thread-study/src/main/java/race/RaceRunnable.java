package race;

import java.util.WeakHashMap;

/**
 * @program: multi-thread-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-07 23:31
 **/

public class RaceRunnable implements Runnable {
    private static String winner;
    final int distance = 100;

    public static void main(String[] args) {
        RaceRunnable raceRunnable = new RaceRunnable();
        new Thread(raceRunnable, "乌龟").start();
        new Thread(raceRunnable, "兔子").start();
    }

    @Override
    public void run() {
        String currRunner = Thread.currentThread().getName();
        for (int i = 0; i <= distance; i++) {
            if (isGameOver(i)) {
                break;
            }
            if ("兔子".equals(currRunner)) {
                i += 2;
                if (i % 10 == 1) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(String.format("%s runs %s far", currRunner, i));
        }
    }

    private boolean isGameOver(int steps) {
        if (winner != null) {
            return true;
        }
        if (steps >= distance) {
            winner = Thread.currentThread().getName();
            System.out.println("winner is " + winner);
            return true;
        }
        return false;
    }
}
