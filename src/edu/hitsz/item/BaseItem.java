package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 道具类。
 * 也可以考虑不同类型的道具
 *
 * @author Canoes
 */
public abstract class BaseItem extends AbstractFlyingObject {


    public BaseItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= -10 || locationX >= Main.WINDOW_WIDTH+10) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }
    
    public abstract void make_influence(HeroAircraft heroAircraft, int data);
}
