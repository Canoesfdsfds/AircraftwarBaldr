package edu.hitsz.bullet;

import edu.hitsz.observers.EnemyBulletex2Observer;
import edu.hitsz.application.Game;

/**
 * @Author hitsz
 */
public class EnemyBulletex2 extends BaseBullet {

    private EnemyBulletex2Observer enemyBulletex2Observer;

    public EnemyBulletex2(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
        enemyBulletex2Observer = new EnemyBulletex2Observer(this);
        Game.bombOB.add_observer(enemyBulletex2Observer);
    }
}
