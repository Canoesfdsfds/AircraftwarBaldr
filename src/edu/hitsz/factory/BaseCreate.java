package edu.hitsz.factory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.hitsz.aircraft.BossAircraft;
import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.factory.For_Aircraft.*;
import edu.hitsz.factory.For_Item.*;
import edu.hitsz.strategy.SuperRingShootStrategy;
import edu.hitsz.strategy.SuperSuperRingShootStrategy;


//内部封装了所有调用工厂类的函数

public class BaseCreate extends AbstractBaseCreate{
    public int topgrow = 8;
    private static volatile BaseCreate instance;
    private static Object lock = new Object();
    private BaseCreate() {}; 
    public static BaseCreate getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new BaseCreate();
                }
            }
        }
        return instance;
    }
    public void CreateEnemy(List<EnemyAircraft> enemyAircrafts, int enemyMaxNumber, AtomicBoolean bossflag, int score, int time) {
        if(grow < topgrow)
        update_grow(time);
        else
        {
            if(time > 30000 + grow * 30000)
            {
                Game.setFinal();
                return;
            }
        }
        if (Math.random() <= 0.8) { //生成概率0.8
            EnemyAircraftFactory factory;
            double randomValue = Math.random();
            Random random = new Random();
            if (randomValue < 0.60) {
                factory = new MobEnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                random.nextInt(6+grow) + 5, // Setting different speedY based on the type of enemy
                250 + 80 * grow  // Setting different hp based on the type of enemy
            ));
            } else if (randomValue > 0.85){
                factory = new Elite2EnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                random.nextInt(3) + 2, // Setting different speedY based on the type of enemy
                400 + 180 * grow  // Setting different hp based on the type of enemy
            ));
            } else {
                factory = new EliteEnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                random.nextInt(10) - 5,
                random.nextInt(2) + 4, // Setting different speedY based on the type of enemy
                400 + 120 * grow  // Setting different hp based on the type of enemy
            ));
            }
        }
    
        if(score >= 1000 + 1000 * Bossindex) // Changed bossflag to bossflag.get()
        {
            BossAircraftFactory bossfactory = new BossAircraftFactory();
            BossAircraft bossAircraft = (BossAircraft) bossfactory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)), 
                (int) (Main.WINDOW_HEIGHT * 0.15),
                2, 0, 5000 + 1000 * grow
            );
            enemyAircrafts.add(bossAircraft); // Add the created BossAircraft to the list
            Bossindex++;
            Game.set_boss_num(true);
            if(grow >= 3)
            bossAircraft.setShootStrategy(new SuperRingShootStrategy());
            if(grow >= 7)
            bossAircraft.setShootStrategy(new SuperSuperRingShootStrategy());
        }
        return;
    }
    public HeroAircraft CreateHero(int locationX, int locationY, int speedX, int speedY, int hp)
    {
        HeroAircraftFactory factory = new HeroAircraftFactory();
        HeroAircraft heroAircraft = (HeroAircraft) factory.createAircraft(locationX, locationY, speedX, speedY, hp);
        heroAircraft.setSize(40, 30);
        return heroAircraft;
    }
}
