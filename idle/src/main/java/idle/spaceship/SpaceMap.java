package idle.spaceship;

/**
 * @author Sean Yu
 */
public class SpaceMap {
    int mapLevel;
    int cost;

    //当前剩余矿数
    int remainingLodeCount = 10;
    int remainingVaultCount = 25;
    int remainingRuins = 25;

    public SpaceMap(int mapLevel, int cost, int remainingLodeCount, int remainingVaultCount, int remainingRuins) {
        this.mapLevel = mapLevel;
        this.cost = cost;
        this.remainingLodeCount = remainingLodeCount;
        this.remainingVaultCount = remainingVaultCount;
        this.remainingRuins = remainingRuins;
    }
}
