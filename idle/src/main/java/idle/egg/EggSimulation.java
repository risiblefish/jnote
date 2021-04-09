package idle.egg;

import java.util.Random;

/**
 * @author Sean Yu
 */
public class EggSimulation {
    static Random random = new Random();

    public static void main(String[] args) {
        int times = 10000;
        int simCount = 100000;
        long totalEgg = 0;
        for (int i = 0 ; i < simCount ; i++){
            totalEgg += sim(times);
        }
        System.out.println(totalEgg /= simCount);
    }

    private static int sim(int times) {
        int missCount = 0;
        int egg = 0;
        for (int i = 0; i < times; i++) {
            if (missCount == 14) {
                egg++;
                missCount = 0;
            } else {
                int num = 1 + random.nextInt(100);
                if (num <= 6) {
                    egg++;
                    missCount = 0;
                } else {
                    missCount++;
                }
            }
        }
        return egg;
    }
}
