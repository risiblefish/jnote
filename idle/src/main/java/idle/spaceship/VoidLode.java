package idle.spaceship;

/**
 * @author Sean Yu
 */
public class VoidLode extends SpaceMapObject {

    private VoidLode(Integer mapLevel, Integer crystal, Integer score, Integer ingot) {
        super(mapLevel, crystal, score, ingot);
    }

    /**
     * 矿脉的水晶和晶锭值取决于采集器等级，
     * 矿脉的探索积分取决于地图等级 ：50 +  (mapLevel - 1) * 15
     * @param mapLevel
     * @param collectorLevel
     * @return
     */
    public static VoidLode getInstance(Integer mapLevel, Integer collectorLevel) {
        assert mapLevel >= 1;
        assert collectorLevel >= 1;
        Collector collector = Collector.getInstance(collectorLevel);
        int crystal = collector.getCrystal();
        int ingot = collector.getIngot();
        int score = (mapLevel - 1) * 15 + 50;
        return new VoidLode(mapLevel, crystal, score, ingot);
    }
}
