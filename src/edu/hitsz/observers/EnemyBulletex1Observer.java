package edu.hitsz.observers;

import edu.hitsz.bullet.EnemyBulletex1;

public class EnemyBulletex1Observer implements Observer{
    private EnemyBulletex1 enemyBulletex1;
    public EnemyBulletex1Observer(EnemyBulletex1 enemyBulletex1)
    {
        this.enemyBulletex1 = enemyBulletex1;
    }
    public void update_bomb()
    {
        enemyBulletex1.vanish();
    }
}
