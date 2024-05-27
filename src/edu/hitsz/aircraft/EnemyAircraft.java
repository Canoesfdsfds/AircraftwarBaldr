package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.strategy.ShootStrategy;
import edu.hitsz.application.Game;

public abstract class EnemyAircraft extends AbstractAircraft{

    public EnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界为默认敌机飞行方式
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    public void destroy()
    {
        decreaseHp(hp);
    }

    public void decreaseHp(int dhp)
    {
        if(notValid())
        {
            return;
        }
        hp -= dhp;
        if(hp <= 0){
            hp=0;
            Game.score += 10;
            vanish();
        }
    }
}
