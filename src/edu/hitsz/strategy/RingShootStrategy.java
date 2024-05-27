package edu.hitsz.strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBulletex2;

public class RingShootStrategy implements ShootStrategy {

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        if(Game.BossShootIndex % 5 == 0)
        {
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY() + aircraft.direction * 2;
            BaseBullet bullet;
            for (int i = 0; i < 48; i++) {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散，形成环形
                double angle = 2 * Math.PI * i / 48;
                int adjustedSpeedX = (int) (Math.cos(angle) * 20); // Adjust speed for circular spread
                int adjustedSpeedY = (int) (Math.sin(angle) * 20);
                bullet = new EnemyBulletex2(x, y, adjustedSpeedX, adjustedSpeedY, power);
                res.add(bullet);
            }
            Game.enemyBullets.addAll(res);
        }
        return res;
    }

}