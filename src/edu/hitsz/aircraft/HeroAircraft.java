package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.strategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

public class HeroAircraft extends OwnAircraft {

    private static volatile HeroAircraft instance;
    private static Object lock = new Object();

    private int shootNum = 1;
    private int power = 30;
    private int direction = -1;


    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power)
     {
        return shootStrategy.shoot(aircraft, power);
     }

    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public static HeroAircraft getInstance(int locationX, int locationY, int speedX, int speedY, int hp) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new HeroAircraft(locationX, locationY, speedX, speedY, hp);
                }
            }
        }
        return instance;
    }

    // Rest of the class remains the same
    @Override
    public int getDirection(){
        return direction;
    }
    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }


    public void HPup(int dhp) {
        hp += dhp;
    }

    public void Powerup(int dpower) {
        power += dpower;
    }
}