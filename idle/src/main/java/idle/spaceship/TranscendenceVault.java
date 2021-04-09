package idle.spaceship;//package com.scp.rabbit_demo.idle.spaceship;
//
///**
// * 时空秘境
// *
// * @author Sean Yu
// */
//public class TranscendenceVault extends SpaceMapObject {
//    private TranscendenceVault(Integer mapLevel, Integer crystal, Integer score, Integer ingot) {
//        super(mapLevel, crystal, score, ingot);
//    }
//
//    /**
//     * 积分和晶锭 ： 50 + (mapLevel - 1 ) * 15
//     *
//     * @param mapLevel
//     * @return
//     */
//    protected static TranscendenceVault getInstance(int mapLevel) {
//        int levelGap = mapLevel - 1;
//        int score = 50 + levelGap * 15;
//        int ingot = 50 + levelGap * 15;
//        return new TranscendenceVault(mapLevel, 0, score, ingot);
//    }
//}
