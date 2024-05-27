package edu.hitsz.strategy;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.EnemyBulletex1;

public class DirectShootStrategy implements ShootStrategy {
    @Override
    //shootnum = 1
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.direction * 2;
        BaseBullet bullet;
        for (int i = 0; i < 1; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBulletex1(x + (i * 2 - 1 + 1) * 20, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 5, power);
            res.add(bullet);
        }
        return res;
    }
}
