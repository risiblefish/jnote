package idle.spaceship;

import java.util.Arrays;

/**
 * @author Sean Yu
 */
public class Player extends SpaceMapObject implements Cloneable {
    //开图所需晶锭,初始化2个0用来对齐下标
    int averageEnableLevel;
    int socrePercent;
    double awardPercent;
    int totalIngot = 0;

    //进入当前地图的天数
    int currMapDays = 1;
    //当前总天数
    int currTotalDays = 0;

    //当前剩余 矿 数量
    int remainingLode = 10;
    //当前剩余 时空秘境&破碎遗迹 数量
    int remainingVaultRuins = 50;
    //当前剩余 空旷之地 数量
    int remainingEmptyArea = 28;

    int[] scoreTrace = new int[32];

    //记录第i天做出的选择：0不作为 1升采集器 2开图， 有可能出现在同一天做出多次选择的情况：比如同一天里既升采集器，又开图，或者采集器连升2级
    StringBuilder optionTrace = new StringBuilder();

    Collector collector = Collector.getInstance(1);

    /**
     * 记录做出的选择
     *
     * @param choice
     */
    public void logOption(int choice) {
        optionTrace.append(String.format("\n第%s天，进行了%s", currTotalDays, resolveChoice(choice)));
//        optionTrace[currTotalDays] = choice;
    }

    private String resolveChoice(int choice) {
        switch (choice) {
            case 0:
                return "不作为";
            case 1:
                return "升采集器";
            case 2:
                return "进入新地图";
        }
        return "";
    }

    /**
     * 深拷贝 ：在每次需要做出选择的时候，进行深拷贝，从而收集所有的结果
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Player clone() throws CloneNotSupportedException {
        Player playerClone = (Player) super.clone();
        Collector collectorClone = collector.clone();
        playerClone.setCollector(collectorClone);
        StringBuilder sbClone = new StringBuilder();
        sbClone.append(optionTrace);
        playerClone.setOptionTrace(sbClone);
        return playerClone;
    }

    /**
     * 默认平均赋能5开始
     * 探索积分加成：100% + 400% + (赋能等级 - 5) * 100%
     * 奖励加成 ： 100% + 50 % + (赋能等级 - 5) * 10%
     *
     * @param averageEnableLevel
     */
    public Player(Integer averageEnableLevel) {
        super(1, 0, 0, 0);
        this.averageEnableLevel = averageEnableLevel;
        this.socrePercent = 5 + (averageEnableLevel - 5);
        this.awardPercent = 1.5 + (averageEnableLevel - 5) * 0.1;
    }

    /**
     * 获得smo对象带来的奖励，并记录总晶锭数
     *
     * @param smo
     */
    public void gain(SpaceMapObject smo) {

        this.crystal += (int) (smo.crystal * awardPercent);
        this.score += smo.score * socrePercent;
        int gainIngot = (int) (smo.ingot * awardPercent);
        this.ingot += gainIngot;
        this.totalIngot += gainIngot;
//        System.out.println(String.format("从%s获得了%s积分,当前总积分%s", smo.getClass().getName(), smo.score * socrePercent, this.score));

    }

    public int getSocrePercent() {
        return socrePercent;
    }

    public void setSocrePercent(int socrePercent) {
        this.socrePercent = socrePercent;
    }

    public double getAwardPercent() {
        return awardPercent;
    }

    public void setAwardPercent(double awardPercent) {
        this.awardPercent = awardPercent;
    }

    public int getTotalIngot() {
        return totalIngot;
    }

    public void setTotalIngot(int totalIngot) {
        this.totalIngot = totalIngot;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public int getAverageEnableLevel() {
        return averageEnableLevel;
    }

    public void setAverageEnableLevel(int averageEnableLevel) {
        this.averageEnableLevel = averageEnableLevel;
    }

    public int getCurrMapDays() {
        return currMapDays;
    }

    public void setCurrMapDays(int currMapDays) {
        this.currMapDays = currMapDays;
    }

    public int getCurrTotalDays() {
        return currTotalDays;
    }

    public void setCurrTotalDays(int currTotalDays) {
        this.currTotalDays = currTotalDays;
        scoreTrace[currTotalDays] = this.score;
    }

    public int getRemainingLode() {
        return remainingLode;
    }

    public void setRemainingLode(int remainingLode) {
        this.remainingLode = remainingLode;
    }

    public int getRemainingVaultRuins() {
        return remainingVaultRuins;
    }

    public void setRemainingVaultRuins(int remainingVaultRuins) {
        this.remainingVaultRuins = remainingVaultRuins;
    }

    public int getRemainingEmptyArea() {
        return remainingEmptyArea;
    }

    public void setRemainingEmptyArea(int remainingEmptyArea) {
        this.remainingEmptyArea = remainingEmptyArea;
    }

    public StringBuilder getOptionTrace() {
        return optionTrace;
    }

    public void setOptionTrace(StringBuilder optionTrace) {
        this.optionTrace = optionTrace;
    }

    @Override
    public String toString() {
        return "Player{" +
                "averageEnableLevel=" + averageEnableLevel +
                ", socrePercent=" + socrePercent +
                ", awardPercent=" + awardPercent +
                ", totalIngot=" + totalIngot +
                ", currMapDays=" + currMapDays +
                ", currTotalDays=" + currTotalDays +
                ", remainingLode=" + remainingLode +
                ", remainingVaultRuins=" + remainingVaultRuins +
                ", remainingEmptyArea=" + remainingEmptyArea +
                ", optionTrace=" + optionTrace +
                ", collectorLevel=" + collector.getLevel() +
                ", mapLevel=" + mapLevel +
                ", crystal=" + crystal +
                ", scoreTrace=" + Arrays.toString(scoreTrace) +
                ", ingot=" + ingot +
                '}';
    }
}
