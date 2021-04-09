package idle.spaceship;

/**
 * 一次水晶掠夺
 *
 * @author Sean Yu
 */
public class CrystalContest extends SpaceMapObject {
    private CrystalContest(Integer mapLevel, Integer crystal, Integer score, Integer ingot) {
        super(mapLevel, crystal, score, ingot);
    }

    /**
     * 积分 = (mapLevel - 1 ) * 15 + 50
     *
     * @param mapLevel
     * @return
     */
    private static CrystalContest createInstance(int mapLevel) {
        int score = 50 + (mapLevel - 1) * 15;
        return new CrystalContest(mapLevel, 0, score, 0);
    }

    private static final CrystalContest L1 = createInstance(1);
    private static final CrystalContest L2 = createInstance(2);
    private static final CrystalContest L3 = createInstance(3);
    private static final CrystalContest L4 = createInstance(4);
    private static final CrystalContest L5 = createInstance(5);
    private static final CrystalContest L6 = createInstance(6);

    public static CrystalContest getInstance(int mapLevel) {
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
