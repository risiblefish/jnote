package idle.spaceship;

/**
 * 空旷之地
 * @author Sean Yu
 */
public class EmptyArea extends SpaceMapObject {

    private EmptyArea(Integer mapLevel, Integer crystal, Integer score, Integer ingot) {
        super(mapLevel, crystal, score, ingot);
    }

    /**
     * 异能水晶 = 250 + (mapLevel - 1) * 75
     * 晶锭 = 50 + (mapLevel - 1) * 15
     *
     * @param mapLevel
     * @return
     */
    private static EmptyArea createInstance(int mapLevel) {
        assert mapLevel >= 1;
        int levelGap = mapLevel - 1;
        int crystal = 250 + levelGap * 75;
        int ingot = 50 + levelGap * 15;
        int score = 0;
        return new EmptyArea(mapLevel, crystal, score, ingot);
    }

    private static final EmptyArea L1 = createInstance(1);
    private static final EmptyArea L2 = createInstance(2);
    private static final EmptyArea L3 = createInstance(3);
    private static final EmptyArea L4 = createInstance(4);
    private static final EmptyArea L5 = createInstance(5);
    private static final EmptyArea L6 = createInstance(6);

    public static EmptyArea getInstance(int mapLevel){
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
