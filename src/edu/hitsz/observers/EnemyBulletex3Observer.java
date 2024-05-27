package edu.hitsz.observers;

import edu.hitsz.bullet.EnemyBulletex3;

public class EnemyBulletex3Observer implements Observer{
    private EnemyBulletex3 enemyBulletex3;
    public EnemyBulletex3Observer(EnemyBulletex3 enemyBulletex3)
    {
        this.enemyBulletex3 = enemyBulletex3;
    }
    public void update_bomb()
    {
        enemyBulletex3.vanish();
    }
}