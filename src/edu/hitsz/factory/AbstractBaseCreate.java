package edu.hitsz.factory;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;

public abstract class AbstractBaseCreate {
    public int Bossindex = 0;
    private static volatile AbstractBaseCreate instance;
    public int grow = 0;
    public abstract void CreateEnemy(List<EnemyAircraft> enemyAircrafts, int enemyMaxNumber, AtomicBoolean bossflag, int score, int time);
    public abstract HeroAircraft CreateHero(int locationX, int locationY, int speedX, int speedY, int hp);
    public void update_grow(int time)
    {
        if(time > 30000 + grow * 30000)
        {
            grow++;
            warnGrow();
        }
    }
    public void warnGrow()
    {
        System.out.println("难度提升！！！ 敌机加强   当前难度："+ grow);
        Game.setHarderindexReady();
    }
}
