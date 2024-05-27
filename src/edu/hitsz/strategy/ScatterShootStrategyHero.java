package edu.hitsz.strategy;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

public class ScatterShootStrategyHero implements ShootStrategy {
    @Override
    //shootnum = 5
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.direction * 2;
        BaseBullet bullet;
        for (int i = 0; i < 11; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            int k = i - 5;
            double angle = 1 * Math.PI * k / 18;
            int adjustedSpeedX = (int) (Math.cos(angle - Math.PI / 2) * 20); // Adjust speed for circular spread
            int adjustedSpeedY = (int) (Math.sin(angle - Math.PI / 2) * 20);
            bullet = new HeroBullet(x + (i * 1 - 6 + 1) * 5, y, adjustedSpeedX, adjustedSpeedY, power);
            res.add(bullet);
        }
        return res;
    }
}
