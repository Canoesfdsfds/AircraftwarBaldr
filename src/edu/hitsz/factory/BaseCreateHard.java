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

public class BaseCreateHard extends AbstractBaseCreate{
    public int topgrow = 9;
    private static volatile BaseCreateHard instance;
    private static Object lock = new Object();
    private BaseCreateHard() {}; 
    public static BaseCreateHard getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new BaseCreateHard();
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
        if (true) { //生成概率1.0
            EnemyAircraftFactory factory;
            double randomValue = Math.random();
            Random random = new Random();
            if (randomValue < 0.60) {
                factory = new MobEnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                random.nextInt(6+grow) + 7, // Setting different speedY based on the type of enemy
                300 + 100 * grow  // Setting different hp based on the type of enemy
            ));
            } else if (randomValue > 0.85){
                factory = new Elite2EnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                random.nextInt(3) + 2, // Setting different speedY based on the type of enemy
                500 + 250 * grow  // Setting different hp based on the type of enemy
            ));
            } else {
                factory = new EliteEnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                random.nextInt(16) - 8,
                random.nextInt(2) + 4, // Setting different speedY based on the type of enemy
                500 + 200 * grow  // Setting different hp based on the type of enemy
            ));
            }
        }
    
        if(score >= 800 + 800 * Bossindex) // Changed bossflag to bossflag.get()
        {
            BossAircraftFactory bossfactory = new BossAircraftFactory();
            BossAircraft bossAircraft = (BossAircraft) bossfactory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)), 
                (int) (Main.WINDOW_HEIGHT * 0.15),
                2, 0, 5000 + 1500 * grow
            );
            enemyAircrafts.add(bossAircraft); // Add the created BossAircraft to the list
            Game.set_boss_num(true);
            Bossindex++;
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

