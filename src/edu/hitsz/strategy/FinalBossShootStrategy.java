package edu.hitsz.strategy;

import java.util.List;
import java.util.LinkedList;
import java.lang.Math;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.EnemyBulletex1;
import edu.hitsz.bullet.EnemyBulletex2;
import edu.hitsz.bullet.EnemyBulletex3;
import edu.hitsz.bullet.EnemyBulletex4;

public class FinalBossShootStrategy implements ShootStrategy {

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        if(Game.FinalBossShootIndex % 12 == 0) {
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY();
            BaseBullet bullet;
            for (int i = 0; i < 24; i++) {
                double angle = 2 * Math.PI * i / 24;
                int adjustedSpeedX = (int) (Math.cos(angle) * 12); // Adjust speed for circular spread
                int adjustedSpeedY = (int) (Math.sin(angle) * 12);
                bullet = new EnemyBulletex3(x, y, adjustedSpeedX, adjustedSpeedY, power);
                res.add(bullet);
            }
        } 

        if(Game.FinalBossShootIndex % 31 == 0) {
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY();
            BaseBullet bullet;
            for (int i = 0; i < 36; i++) {
                double angle = 2 * Math.PI * i / 36;
                int adjustedSpeedX = (int) (Math.cos(angle) * 12); // Adjust speed for circular spread
                int adjustedSpeedY = (int) (Math.sin(angle) * 12);
                bullet = new EnemyBulletex1(x, y, adjustedSpeedX, adjustedSpeedY, power);
                res.add(bullet);
            }
        } 
        
        if(Game.FinalBossShootCount % 3 == 0){
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY();
            int xl = x - 100;
            int xr = x + 100;
            int herox = Game.heroAircraft.getLocationX();
            int heroy = Game.heroAircraft.getLocationY();

            // 计算左右两侧子弹的速度和角度
            double anglel = Math.atan2(heroy - y, xl - herox); // 计算左侧子弹的角度
            double angler = Math.atan2(heroy - y, xr - herox); // 计算右侧子弹的角度
            int speed = 25; // 设定一个合理的速度

            // 计算左右两侧子弹的速度分量
            int speedxl = (int) (speed * Math.cos(anglel));
            int speedyl = (int) (speed * Math.sin(anglel));
            int speedxr = (int) (speed * Math.cos(angler));
            int speedyr = (int) (speed * Math.sin(angler));

            // 创建左右两侧子弹并加入到结果列表中
            BaseBullet bulletl = new EnemyBulletex2(xl, y, -speedxl, speedyl, power);
            BaseBullet bulletr = new EnemyBulletex2(xr, y, -speedxr, speedyr, power);
            res.add(bulletl);
            res.add(bulletr);
        }
        else if (Game.FinalBossShootCount % 3 == 1) {
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY();
            BaseBullet bullet;
            for (int i = 0; i < 24; i++) {
                double angle = 2 * Math.PI * i / 24;
                int adjustedSpeedX = (int) (Math.cos(angle) * 20); // Adjust speed for circular spread
                int adjustedSpeedY = (int) (Math.sin(angle) * 20);
                bullet = new EnemyBulletex3(x, y, adjustedSpeedX, adjustedSpeedY, power);
                res.add(bullet);
            }
        }
        else if (Game.FinalBossShootCount % 3 == 2){
            BaseBullet bullet;
            int x = aircraft.getLocationX();
            int y = aircraft.getLocationY();
            for(int i = 0 ; i < 40 ; i++){
                bullet = new EnemyBulletex4(x, y - 800 + 40 * i - 100, 0, 50, 100);
                res.add(bullet);
            }
        }
        Game.enemyBullets.addAll(res);
        return res;
    }
}