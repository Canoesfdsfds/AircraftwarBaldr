package edu.hitsz.strategy;

import java.util.List;
import java.util.LinkedList;
import java.lang.Math;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBulletex1;
import edu.hitsz.bullet.EnemyBulletex2;

public class SuperRingShootStrategy implements ShootStrategy {

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        if(Game.BossShootIndex % 5 == 0) {
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY();
            BaseBullet bullet;
            for (int i = 0; i < 48; i++) {
                double angle = 2 * Math.PI * i / 48;
                int adjustedSpeedX = (int) (Math.cos(angle) * 20); // Adjust speed for circular spread
                int adjustedSpeedY = (int) (Math.sin(angle) * 20);
                bullet = new EnemyBulletex2(x, y, adjustedSpeedX, adjustedSpeedY, power);
                res.add(bullet);
            }
            Game.enemyBullets.addAll(res);
        } else {
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY();
            int xl = x - 100;
            int xr = x + 100;
            int herox = Game.heroAircraft.getLocationX();
            int heroy = Game.heroAircraft.getLocationY();

            // 计算左右两侧子弹的速度和角度
            double anglel = Math.atan2(heroy - y, xl - herox); // 计算左侧子弹的角度
            double angler = Math.atan2(heroy - y, xr - herox); // 计算右侧子弹的角度
            int speed = 15; // 设定一个合理的速度

            // 计算左右两侧子弹的速度分量
            int speedxl = (int) (speed * Math.cos(anglel));
            int speedyl = (int) (speed * Math.sin(anglel));
            int speedxr = (int) (speed * Math.cos(angler));
            int speedyr = (int) (speed * Math.sin(angler));

            // 创建左右两侧子弹并加入到结果列表中
            BaseBullet bulletl = new EnemyBulletex1(xl, y, -speedxl, speedyl, power);
            BaseBullet bulletr = new EnemyBulletex1(xr, y, -speedxr, speedyr, power);
            res.add(bulletl);
            res.add(bulletr);
            Game.enemyBullets.addAll(res);
        }
        return res;
    }
}