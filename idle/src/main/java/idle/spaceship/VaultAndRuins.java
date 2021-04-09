package idle.spaceship;

/**
 * 破碎遗迹 和 时空秘境
 *
 * 由于2者 晶锭 和 积分相同 故设为1个对象
 * @author Sean Yu
 */
public class VaultAndRuins extends SpaceMapObject {

    private VaultAndRuins(Integer mapLevel, Integer crystal, Integer score, Integer ingot) {
        super(mapLevel, crystal, score, ingot);
    }

    /**
     * 探索积分 = 50 + (mapLevel - 1) * 15
     * 晶锭 = 50 + (mapLevel - 1) * 15
     *
     * @param mapLevel
     * @return
     */
    private static VaultAndRuins createInstance(int mapLevel) {
        int levelGap = mapLevel - 1;
        int score = 50 + levelGap * 15;
        int ingot = 50 + levelGap * 15;
        return new VaultAndRuins(mapLevel, 0, score, ingot);
    }

    private static final VaultAndRuins L1 = createInstance(1);
    private static final VaultAndRuins L2 = createInstance(2);
    private static final VaultAndRuins L3 = createInstance(3);
    private static final VaultAndRuins L4 = createInstance(4);
    private static final VaultAndRuins L5 = createInstance(5);
    private static final VaultAndRuins L6 = createInstance(6);

    public static VaultAndRuins getInstance(int mapLevel) {
        assert mapLevel >= 1 && mapLevel <= 6;
        switch (mapLevel) {
            case 1:
                return L1;
            case 2:
                return L2;
            case 3:
                return L3;
            case 4:
                return L4;
            case 5:
                return L5;
            case 6:
                return L6;
            default:
                return null;
        }
    }
}
