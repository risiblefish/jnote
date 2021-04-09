package idle.spaceship;//package com.scp.rabbit_demo.idle.spaceship;
//
///**
// * 破碎遗迹
// * @author Sean Yu
// */
//public class WreckedRuins extends SpaceMapObject {
//
//    private WreckedRuins(Integer mapLevel, Integer crystal, Integer score, Integer ingot) {
//        super(mapLevel, crystal, score, ingot);
//    }
//
//    /**
//     * 探索积分 = 50 + (mapLevel - 1) * 15
//     * 晶锭 = 50 + (mapLevel - 1) * 15
//     *
//     * @param mapLevel
//     * @return
//     */
//    protected static WreckedRuins getInstance(int mapLevel) {
//        int levelGap = mapLevel - 1;
//        int score = 50 + levelGap * 15;
//        int ingot = 50 + levelGap * 15;
//        return new WreckedRuins(mapLevel, 0, score, ingot);
//    }
//}
