package edu.hitsz.bullet;

import edu.hitsz.observers.EnemyBulletex3Observer;
import edu.hitsz.application.Game;

/**
 * @Author hitsz
 */
public class EnemyBulletex3 extends BaseBullet {

    private EnemyBulletex3Observer enemyBulletex3Observer;

    public EnemyBulletex3(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
        enemyBulletex3Observer = new EnemyBulletex3Observer(this);
        Game.bombOB.add_observer(enemyBulletex3Observer);
    }
}