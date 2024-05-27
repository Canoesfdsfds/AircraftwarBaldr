package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.factory.For_Item.BoomItemFactory;
import edu.hitsz.factory.For_Item.HPupItemFactory;
import edu.hitsz.factory.For_Item.ItemFactory;
import edu.hitsz.factory.For_Item.PowerUpItemFactory;
import edu.hitsz.factory.For_Item.SpPowerUpItemFactory;
import edu.hitsz.item.BaseItem;
import edu.hitsz.strategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * Boss机
 * 能发射子弹
 * @author Canoes
 */

 public class BossAircraft extends EnemyAircraft {
    /**
     * 子弹一次发射数量
     */
    private int shootNum = 48; // Increase shootNum for a denser ring of bullets

    /**
     * 子弹伤害
     */
    private int power = 50; // Increase power for more damaging bullets

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    public BossAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    public void forward() {
        locationX += speedX;
        locationY += speedY;
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            // 横向超出边界后反向
            speedX = -speedX;
        }
    }

    public List<BaseItem> fall(){//掉落道具
        List<BaseItem> Items = new LinkedList<>();
        for(int i = 0 ; i < 1 ; i++){
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
        }
        return Items;
    }
}
