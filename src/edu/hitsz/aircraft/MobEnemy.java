package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.observers.MobObserver;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.application.Game;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends EnemyAircraft {
    private MobObserver mobObserver;

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        mobObserver = new MobObserver(this);
        Game.bombOB.add_observer(mobObserver);
    }

    public void forward() {
        locationX += speedX;
        locationY += 1.5 * speedY;//冲撞速度较快
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            // 横向超出边界后反向
            speedX = -speedX;
        }
    }


}
