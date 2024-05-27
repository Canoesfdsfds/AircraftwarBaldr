package edu.hitsz.bullet;

import edu.hitsz.observers.EnemyBulletex4Observer;
import edu.hitsz.application.Game;

/**
 * @Author hitsz
 */
public class EnemyBulletex4 extends BaseBullet {

    private EnemyBulletex4Observer enemyBulletex4Observer;

    public EnemyBulletex4(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
        enemyBulletex4Observer = new EnemyBulletex4Observer(this);
        Game.bombOB.add_observer(enemyBulletex4Observer);
    }
}