package edu.hitsz.observers;

import edu.hitsz.bullet.EnemyBulletex4;

public class EnemyBulletex4Observer implements Observer{
    private EnemyBulletex4 enemyBulletex4;
    public EnemyBulletex4Observer(EnemyBulletex4 enemyBulletex4)
    {
        this.enemyBulletex4 = enemyBulletex4;
    }
    public void update_bomb()
    {
        enemyBulletex4.vanish();
    }
}