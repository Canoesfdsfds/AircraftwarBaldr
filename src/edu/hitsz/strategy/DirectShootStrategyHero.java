package edu.hitsz.strategy;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

public class DirectShootStrategyHero implements ShootStrategy {
    @Override
    //shootnum = 2
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.direction * 2;
        BaseBullet bullet;
        for (int i = 0; i < 2; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(x + (i * 2 - 2 + 1) * 10, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
            res.add(bullet);
        }
        return res;
    }
}