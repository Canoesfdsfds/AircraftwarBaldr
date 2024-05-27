package edu.hitsz.bullet;

import edu.hitsz.observers.EnemyBulletObserver;
import edu.hitsz.application.Game;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet {

    private EnemyBulletObserver enemyBulletObserver;

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
        enemyBulletObserver = new EnemyBulletObserver(this);
        Game.bombOB.add_observer(enemyBulletObserver);
    }
}
