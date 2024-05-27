package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.item.BaseItem;
import edu.hitsz.observers.Elite1Observer;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.factory.For_Item.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英敌机
 * 能发射子弹
 * 死亡掉落道具
 * @author Canoes
 */

public class EliteEnemy extends EnemyAircraft{
    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 3;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    private Elite1Observer elite1Observer;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        elite1Observer = new Elite1Observer(this);
        Game.bombOB.add_observer(elite1Observer);
    }



    public List<BaseItem> fall(){//掉落道具
        List<BaseItem> Items = new LinkedList<>();
        ItemFactory factory;
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 4;
        int speedY = this.getSpeedY() + direction*1;
        double randnum = Math.random();
        if(randnum < 0.55)
        {
            factory = new HPupItemFactory();
            Items.add(factory.CreateItem(x, y, speedX, speedY));
        }
        else if(randnum < 0.77)
        {
            factory = new PowerUpItemFactory();
            Items.add(factory.CreateItem(x, y, speedX, speedY));
        }
        else if(randnum < 0.88)
        {
            factory = new SpPowerUpItemFactory();
            Items.add(factory.CreateItem(x, y, speedX, speedY));
        }
        else
        {
            factory = new BoomItemFactory();
            Items.add(factory.CreateItem(x, y, speedX, speedY));
        }
        return Items;
    }
}
