package edu.hitsz.bullet;

import edu.hitsz.observers.EnemyBulletex1Observer;
import edu.hitsz.application.Game;

/**
 * @Author hitsz
 */
public class EnemyBulletex1 extends BaseBullet {

    private EnemyBulletex1Observer enemyBulletex1Observer;

    public EnemyBulletex1(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
        enemyBulletex1Observer = new EnemyBulletex1Observer(this);
        Game.bombOB.add_observer(enemyBulletex1Observer);
    }
}

