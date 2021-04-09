package idle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.LongStream;

/**
 * @author Sean Yu
 */
public class Test {

    public static void main(String[] args) {
        Map<String, Integer> simulateMap = new HashMap();
        Map<String, Integer> countMap = new HashMap();
        long n = 10000;
        LongStream.rangeClosed(1, n).forEach(i -> simulate(simulateMap,countMap));
        System.out.println(countMap.toString());
    }

    static void simulate(Map<String, Integer> simulateMap, Map<String, Integer> countMap){
        Random random = new Random();

        int sim1 = random.nextInt(100);
        setValue(simulateMap, countMap, sim1);

        int sim2 = random.nextInt(100);
        setValue(simulateMap, countMap, sim2);

        int sim3 = random.nextInt(100);
        setValue(simulateMap, countMap, sim3);

        int sim4 = random.nextInt(100);
        setValue(simulateMap, countMap, sim4);

        int sim5 = random.nextInt(100);
        setValue(simulateMap, countMap, sim5);

        count(simulateMap, countMap);
        simulateMap.clear();
    }

    static void setValue(Map<String, Integer> map, Map<String, Integer> countMap, int curr) {
        if (curr < 24) {
            map.put("a", map.getOrDefault("a", 0) + 1);
        } else if (curr < 48) {
            map.put("b", map.getOrDefault("b", 0) + 1);
        } else if (curr < 72) {
            map.put("c", map.getOrDefault("c", 0) + 1);
        } else if (curr < 96) {
            map.put("d", map.getOrDefault("d", 0) + 1);
        } else if (curr < 98) {
            map.put("e", map.getOrDefault("e", 0) + 1);
        } else {
            map.put("f", map.getOrDefault("f", 0) + 1);
        }
    }

    static void count(Map<String, Integer> map, Map<String, Integer> countMap) {
        int max = 1;
        for (Integer curr : map.values()) {
            if (curr > max) {
                max = curr;
            }
        }
        switch (max) {
            case 1: {
                countMap.put("5张不同", countMap.getOrDefault("5张不同", 0) + 1);
                break;
            }
            case 2: {
                countMap.put("2张相同", countMap.getOrDefault("2张相同", 0) + 1);
                break;
            }
            case 3: {
                countMap.put("3张相同", countMap.getOrDefault("3张相同", 0) + 1);
                break;
            }
            case 4: {
                countMap.put("4张相同", countMap.getOrDefault("4张相同", 0) + 1);
                break;
            }
            case 5: {
                countMap.put("5张相同", countMap.getOrDefault("5张相同", 0) + 1);
                break;
            }
        }
    }
}
