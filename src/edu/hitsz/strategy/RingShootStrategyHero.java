package edu.hitsz.strategy;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

public class RingShootStrategyHero implements ShootStrategy {
    @Override
    //shootnum = 18
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.direction * 2;
        BaseBullet bullet;
        for (int i = 0; i < 18; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散，形成环形
            double angle = 2 * Math.PI * i / 18;
            int adjustedSpeedX = (int) (Math.cos(angle) * 10); // Adjust speed for circular spread
            int adjustedSpeedY = (int) (Math.sin(angle) * 10);
            bullet = new HeroBullet(x, y, adjustedSpeedX, adjustedSpeedY-30, power);
            res.add(bullet);
        }
        return res;
    }
}
