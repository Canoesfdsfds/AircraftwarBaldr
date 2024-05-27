package edu.hitsz.strategy;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.bullet.HeroBulletex1;

public class LastPowerStrategyHero implements ShootStrategy {
    private int[] arr1 = {0,1,2,3,4,5,4,3,2,1,0,-1,-2,-3,-4,-5,-4,-3,-2,-1};
    private int[] arr2 = {0,1,2,3,2,1,0,-1,-2,-3,-2,-1};
    @Override
    //shootnum = 2
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        List<BaseBullet> res = new LinkedList<>();
        int index1 = Game.HeroShooIndex % 20;
        int index2 = Game.HeroShooIndex % 12;
        int index3 = Game.HeroShooIndex % 2;
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.direction * 2;
        BaseBullet bullet;
        bullet = new HeroBullet(x - 25 - arr1[index1] * 3, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
        res.add(bullet);//左侧子弹1
        bullet = new HeroBullet(x + 25 + arr1[index1] * 3, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
        res.add(bullet);//右侧子弹1
        bullet = new HeroBullet(x - 25 - arr2[index2] * 7, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
        res.add(bullet);//左侧子弹2
        bullet = new HeroBullet(x + 25 + arr2[index2] * 7, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
        res.add(bullet);//右侧子弹2
        bullet = new HeroBullet(x - 50 - arr1[index1] * 3, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
        res.add(bullet);//左侧子弹1
        bullet = new HeroBullet(x + 50 + arr1[index1] * 3, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
        res.add(bullet);//右侧子弹1
        bullet = new HeroBullet(x - 50 - arr2[index2] * 7, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
        res.add(bullet);//左侧子弹2
        bullet = new HeroBullet(x + 50 + arr2[index2] * 7, y, aircraft.getSpeedX(), aircraft.getSpeedY() + aircraft.getDirection() * 20, power);
        res.add(bullet);//右侧子弹2
        if(index3  == 0){
            for (int i = 0; i < 11; i++) {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                int k = i - 5;
                double angle = 1 * Math.PI * k / 12;
                int adjustedSpeedX = (int) (Math.cos(angle - Math.PI / 2) * 25); // Adjust speed for circular spread
                int adjustedSpeedY = (int) (Math.sin(angle - Math.PI / 2) * 25);
                bullet = new HeroBullet(x + (i * 1 - 6 + 1) * 3, y, adjustedSpeedX, adjustedSpeedY, power);
                res.add(bullet);
            }
        }
        else{
            for(int i = 0 ; i < 9 ; i++)
            {
                int j = 8 - i;
                bullet = new HeroBulletex1(x+7*i, y-10*j, 0, -32, power);
                res.add(bullet);
                bullet = new HeroBulletex1(x-7*i, y-10*j, 0, -32, power);
                res.add(bullet);
            }
        }
        return res;
    }
}
