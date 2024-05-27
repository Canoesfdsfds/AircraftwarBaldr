package edu.hitsz.observers;

import edu.hitsz.bullet.EnemyBullet;

public class EnemyBulletObserver implements Observer{
    private EnemyBullet enemyBullet;
    public EnemyBulletObserver(EnemyBullet enemyBullet)
    {
        this.enemyBullet = enemyBullet;
    }
    public void update_bomb()
    {
        enemyBullet.vanish();
    }
}
