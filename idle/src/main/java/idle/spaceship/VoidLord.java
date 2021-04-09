package idle.spaceship;

/**
 * 领主挑战
 *
 * @author Sean Yu
 */
public class VoidLord extends SpaceMapObject {
    private VoidLord(Integer mapLevel, Integer crystal, Integer score, Integer ingot) {
        super(mapLevel, crystal, score, ingot);
    }

    /**
     * 默认过图之前能打完
     * <p>
     * 异能水晶 ：10000, 14000, 18000, 21000, 25000, 28000
     * 探索积分： 1500 + (mapLevel - 1 ) * 450
     * 晶锭： 750 + (mapLevel - 1 ) * 225
     *
     * @param mapLevel
     * @return
     */
    public static VoidLord getInstance(int mapLevel) {
        assert mapLevel >= 1;
        int crystal = 0;
        switch (mapLevel) {
            case 1:
                crystal = 10000;
                break;
            case 2:
                crystal = 14000;
                break;
            case 3:
                crystal = 18000;
                break;
            case 4:
                crystal = 21000;
                break;
            case 5:
                crystal = 25000;
                break;
            case 6:
                crystal = 28000;
                break;
            default:
                break;
        }
        int levelGap = mapLevel - 1;
        int score = 1500 + levelGap * 450;
        int ingot = 750 + levelGap * 225;
        return new VoidLord(mapLevel, crystal, score, ingot);
    }
}
