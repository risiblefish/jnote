package idle;

import com.scp.rabbit_demo.idle.spaceship.*;

import java.util.*;

/**
 * 假设每张图平均包含：
 * 28个空旷之地
 * 25个时空秘境
 * 25个破碎遗迹
 * 10个虚空矿脉
 * 1个boss
 * 每日3个掠夺点
 * <p>
 * 问题： 可以做多种选择，晶锭升采集器or晶锭开图
 * 目的： 30天内，结晶最大，积分最高
 * <p>
 * 前置约定：每日可以7个空旷之地，3次成功掠夺，每张图必过1个boss，不卡矿（即每日15个派遣点），在第6张图之前，达到90%立即返航
 *
 * @author Sean Yu
 */
public class Simulator {
    private final int[] UNLOCK_MAP_INGOT_COST = new int[]{0, 0, 3900, 5100, 6300, 7400, 8600};

    public static void main(String[] args) throws CloneNotSupportedException {
        Simulator sim = new Simulator();
        Player player = new Player(8);

        //当一个player结束了自己的行程，就把自己加到这个list里
        List<Player> finishedPlayerList = new LinkedList<>();
        Stack<Player> researchingPlayerList = new Stack<>();
        researchingPlayerList.push(player);
        while (!researchingPlayerList.isEmpty()) {
            sim.research(researchingPlayerList.pop(), finishedPlayerList, researchingPlayerList);
        }
        System.out.println(finishedPlayerList.size());

//        Optional<Player> maxIngotPlayer = finishedPlayerList.stream().max(Comparator.comparingInt(Player::getTotalIngot));
        Optional<Player> maxScorePlayer = finishedPlayerList.stream().max(Comparator.comparingInt(Player::getScore));
        Optional<Player> maxCrystalPlayer = finishedPlayerList.stream().max(Comparator.comparingInt(Player::getCrystal));
//        maxIngotPlayer.ifPresent(System.out::println);
        System.out.println("最大积分的策略：");
        maxScorePlayer.ifPresent(System.out::println);
        System.out.println("最多水晶的策略：");
        maxCrystalPlayer.ifPresent(System.out::println);
    }

    public void research(Player player, List<Player> finishedPlayerList, Stack<Player> researchingPlayerStack) throws CloneNotSupportedException {
        if (player.getCurrTotalDays() > 25) {
            finishedPlayerList.add(player);
            return;
        }
        /**
         * 打领主：
         * 前2张图默认在进图第1天打完，第3，4张图默认在进图第2天打完，第5，6张图默认在进图第3天打完
         */
        gainLord(player);
        //进行每日3次掠夺
        CrystalContest contest = CrystalContest.getInstance(player.getMapLevel());
        for (int i = 0; i < 3; i++) {
            player.gain(contest);
        }
        //进行每日7次空旷之地探索
        EmptyArea ea = EmptyArea.getInstance(player.getMapLevel());
        for (int i = 0; i < 7; ++i) {
            player.gain(ea);
        }
        player.setRemainingEmptyArea(player.getRemainingEmptyArea() - 7);

        /**
         * 1.当天不能返航：
         * (1)当天不能返航-不升采集器
         * 总天数+1，进图天数+1
         *
         * (2)当天不能返航-升采集器
         * 总天数+1，进图天数+1 晶锭更新，采集器更新
         *
         * 2. 当天能返航：
         *
         * (3)当天能返航-不升采集器-开图成功
         * （总天数不变) 进图天数更新为1，剩余派遣点更新, 晶锭更新，地图等级+1
         *
         * (4)当天能返航-不升采集器（因为资源不够）-开图失败（因为资源不够）
         * （总天数不变) 进图天数更新为1，剩余派遣点更新
         *
         * (5)当天能返航-不升采集器（资源够但不升）-开图失败（资源够但不开）
         *  这种情况必然不是最优解，不考虑
         *
         * (6)当天能返航-升采集器-开图成功
         * （总天数不变) 进图天数更新为1，剩余派遣点更新, 晶锭更新，地图等级+1，采集器更新
         *
         * (7)当天能返航-升采集器-开图失败
         * （总天数不变) 进图天数更新为1，剩余派遣点更新, 晶锭更新，采集器更新
         */
        if (isReturnable(player)) {
            player.setCurrMapDays(1);
            player.setRemainingLode(10);
            player.setRemainingVaultRuins(50);
            player.setRemainingEmptyArea(28);

            boolean collectorUpgradable = isCollectorUpgradable(player);
            boolean nextMapUnlockable = isNextMapUnlockable(player);

            //(4)
            if (!collectorUpgradable && !nextMapUnlockable) {
                Player clone4 = player.clone();
                researchingPlayerStack.push(clone4);
            }

            //(3)
            if (nextMapUnlockable) {
                Player clone3 = player.clone();
                enterNextMap(clone3);
                researchingPlayerStack.push(clone3);
            }

            if (isCollectorUpgradable(player)) {
                //(6)
                Player clone6 = player.clone();
                researchingPlayerStack.push(clone6);

                //(7)
                Player clone5 = player.clone();
                upgradeCollector(clone5);
                if (isNextMapUnlockable(clone5)) {
                    enterNextMap(clone5);
                    researchingPlayerStack.push(clone5);
                }
            }
        }
        //如果不能返航
        else {
            //结算派遣点收益
            //预留采矿人数, 当日可采矿数量 = 采集器数量 ：24小时/采集器生产间隔 取下整
            int lodeTimes = 24 * 3600 / player.getCollector().getProduceIntervalSec();
            lodeTimes = Math.min(lodeTimes, player.getRemainingLode());

            //先进行时空秘境/破碎派遣（由于时空秘境和破碎遗迹的 晶锭 和 积分 收益相同，故归为一类）
            int remainingCount = 15 - lodeTimes;
            VaultAndRuins vr = VaultAndRuins.getInstance(player.getMapLevel());
            for (int i = 0; i < remainingCount; ++i) {
                player.gain(vr);
            }
            player.setRemainingVaultRuins(player.getRemainingVaultRuins() - remainingCount);

            //最后进行采矿
            for (int i = 0; i < lodeTimes; ++i) {
                player.gain(VoidLode.getInstance(player.getMapLevel(), player.getCollector().getLevel()));
            }
            //如果采集器数量>矿数，则剩余矿数记为0
            player.setRemainingLode(Math.max(player.getRemainingLode() - lodeTimes, 0));

            player.setCurrTotalDays(player.getCurrTotalDays() + 1);
            player.setCurrMapDays(player.getCurrMapDays() + 1);
            //(1)
            Player clone1 = player.clone();
            researchingPlayerStack.push(clone1);
            //(2)
            if (isCollectorUpgradable(player)) {
                Player clone2 = player.clone();
                upgradeCollector(clone2);
                researchingPlayerStack.push(clone2);
            }
        }
    }

    private void gainLord(Player player) {
        int mapLevel = player.getMapLevel();
        int currMapDays = player.getCurrMapDays();
        boolean conditionOne = mapLevel <= 2 && currMapDays == 1;
        boolean conditionTwo = mapLevel > 2 && mapLevel <= 4 && currMapDays == 2;
        boolean conditionThree = mapLevel > 4 && mapLevel <= 6 && currMapDays == 3;
        if (conditionOne || conditionTwo || conditionThree) {
            VoidLord lord = VoidLord.getInstance(player.getMapLevel());
            player.gain(lord);
        }
    }

    private boolean isNextMapUnlockable(Player player) {
        int mapLevel = player.getMapLevel();
        if (mapLevel > 5) {
            return false;
        }
        int mapCost = UNLOCK_MAP_INGOT_COST[mapLevel + 1];
        if (player.getIngot() >= mapCost) {
            return true;
        }
        return false;
    }

    /**
     * 能进图的前提是能返航，所以返航产生的更新（更新剩余派遣点数量，更新进图天数为1），此处不再更新
     * 只做比起返航而言的额外更新： 晶锭更新，地图等级+1
     *
     * @param player
     */
    private void enterNextMap(Player player) {
        int mapCost = UNLOCK_MAP_INGOT_COST[player.getMapLevel() + 1];
        player.setMapLevel(player.getMapLevel() + 1);
        player.setIngot(player.getIngot() - mapCost);
        player.logOption(2);
    }

    private boolean isCollectorUpgradable(Player player) {
        if (player.getCollector().getLevel() == 6) {
            return false;
        }
        Collector newCollector = Collector.getInstance(player.getCollector().getLevel() + 1);
        int remainingIngot = player.getIngot() - newCollector.getUpgradeIngotCost();
        if (remainingIngot >= 0) {
            return true;
        }
        return false;
    }

    private void upgradeCollector(Player player) {
        Collector newCollector = Collector.getInstance(player.getCollector().getLevel() + 1);
        int remainingIngot = player.getIngot() - newCollector.getUpgradeIngotCost();
        player.setIngot(remainingIngot);
        player.setCollector(newCollector);
        player.logOption(1);
    }

    private boolean isReturnable(Player player) {
        int totalRemain = player.getRemainingEmptyArea() + player.getRemainingLode() + player.getRemainingVaultRuins();
        return totalRemain / (10 + 50 + 28.0) <= 0.1;
    }
}
