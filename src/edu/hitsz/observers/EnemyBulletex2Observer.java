package edu.hitsz.observers;

import edu.hitsz.bullet.EnemyBulletex2;

public class EnemyBulletex2Observer implements Observer{
    private EnemyBulletex2 enemyBulletex2;
    public EnemyBulletex2Observer(EnemyBulletex2 enemyBulletex2)
    {
        this.enemyBulletex2 = enemyBulletex2;
    }
    public void update_bomb()
    {
        enemyBulletex2.vanish();
    }
}
