package idle.spaceship;

/**
 * @author Sean Yu
 */
public class Collector implements Cloneable {
    private int level;
    private int numLimit;
    private int produceIntervalSec;
    private int crystal;
    private int ingot;
    private int upgradeIngotCost;

    private Collector(int level, int numLimit, int produceIntervalSec, int crystal, int ingot, int upgradeIngotCost) {
        this.level = level;
        this.numLimit = numLimit;
        this.produceIntervalSec = produceIntervalSec;
        this.crystal = crystal;
        this.ingot = ingot;
        this.upgradeIngotCost = upgradeIngotCost;
    }

    @Override
    public Collector clone() throws CloneNotSupportedException {
        return (Collector) super.clone();
    }

    /**
     * 存储上限 = level + 1
     * 生产时间 = 36000 - (level - 1) * 3600
     * 异能水晶 = 2000 + (level - 1) * 600
     * 晶锭 = 50 + (level - 1) * 15
     * 升级所需晶锭 = 1级 ？ 0 ：(level - 2) * 1000 + 3950
     *
     * @param level
     * @return
     */
    public static Collector getInstance(int level) {
        assert level >= 1;
        int levelGap = level - 1;
        int numLimit = level + 1;
        int produceIntervalSec = 36000 - levelGap * 3600;
        int crystal = 2000 + levelGap * 600;
        int ingot = 50 + levelGap * 15;
        int upgradeIngotCost = level == 1 ? 0 : 3950 + (level - 2) * 1000;
        return new Collector(level, numLimit, produceIntervalSec, crystal, ingot, upgradeIngotCost);
    }

    public int getLevel() {
        return level;
    }

    public int getNumLimit() {
        return numLimit;
    }

    public int getProduceIntervalSec() {
        return produceIntervalSec;
    }

    public int getCrystal() {
        return crystal;
    }

    public int getIngot() {
        return ingot;
    }

    public int getUpgradeIngotCost() {
        return upgradeIngotCost;
    }
}
