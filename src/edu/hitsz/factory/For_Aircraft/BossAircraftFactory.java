package edu.hitsz.factory.For_Aircraft;

import edu.hitsz.aircraft.BossAircraft;
import edu.hitsz.strategy.RingShootStrategy;
import edu.hitsz.aircraft.AbstractAircraft;

/**
 * BossAircraft 工厂类
 */
public class BossAircraftFactory extends EnemyAircraftFactory {

    /**
     * 创建 BossAircraft 实例
     *
     * @param locationX 初始 x 轴位置
     * @param locationY 初始 y 轴位置
     * @param speedX     x 轴移动速度
     * @param speedY     y 轴移动速度
     * @param hp         初始生命值
     * @return BossAircraft 实例
     */
    @Override
    public AbstractAircraft createAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        AbstractAircraft aircraft = new BossAircraft(locationX, locationY, speedX, speedY, hp);
        aircraft.setShootStrategy(new RingShootStrategy());
        return aircraft;
    }
}
