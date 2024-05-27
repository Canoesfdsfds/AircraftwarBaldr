package edu.hitsz.strategy;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

public class ScatterShootStrategy implements ShootStrategy {
    @Override
    //shootnum = 5
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.direction * 2;
        BaseBullet bullet;
        for (int i = 0; i < 5; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i * 2 - 5 + 1) * 40, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 5, power);
            res.add(bullet);
        }
        return res;
    }
}
