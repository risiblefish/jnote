package idle.spaceship;

/**
 *
 * 抽象类：
 * 每个地图里的东西，可能包含 异能水晶crystal，探索积分score， 虚空晶锭ingot  (星尘，古木，时空结晶，星尘碎片 权重相对较低，暂不考虑)
 *
 * @author Sean Yu
 */
public abstract class SpaceMapObject {
    int mapLevel;
    int crystal;
    int score;
    int ingot;

    public SpaceMapObject(int mapLevel, int crystal, int score, int ingot) {
        this.mapLevel = mapLevel;
        // 默认开启月卡30%加成，异能水晶会 * 1.3
        this.crystal = (int)(crystal * 1.3);
        this.score = score;
        this.ingot = ingot;
    }

    public int getMapLevel() {
        return mapLevel;
    }

    public int getCrystal() {
        return crystal;
    }

    public int getScore() {
        return score;
    }

    public int getIngot() {
        return ingot;
    }

    public void setMapLevel(int mapLevel) {
        this.mapLevel = mapLevel;
    }

    public void setCrystal(int crystal) {
        this.crystal = crystal;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setIngot(int ingot) {
        this.ingot = ingot;
    }
}
