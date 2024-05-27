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


//内部封装了所有调用工厂类的函数

public class BaseCreateEasy extends AbstractBaseCreate{
    public int topgrow = 0;//难度不变化
    private static volatile BaseCreateEasy instance;
    private static Object lock = new Object();
    private BaseCreateEasy() {}; 
    public static BaseCreateEasy getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new BaseCreateEasy();
                }
            }
        }
        return instance;
    }
    public void CreateEnemy(List<EnemyAircraft> enemyAircrafts, int enemyMaxNumber, AtomicBoolean bossflag, int score, int time) {
        if(grow < topgrow)
        update_grow(time);
        if (Math.random() <= 0.6) { //生成概率0.6
            EnemyAircraftFactory factory;
            double randomValue = Math.random();
            Random random = new Random();
            if (randomValue < 0.60) {
                factory = new MobEnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                random.nextInt(6) + 5, // Setting different speedY based on the type of enemy
                250 + 60 * grow  // Setting different hp based on the type of enemy
            ));
            } else if (randomValue > 0.85){
                factory = new Elite2EnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                random.nextInt(3) + 2, // Setting different speedY based on the type of enemy
                400 + 150 * grow  // Setting different hp based on the type of enemy
            ));
            } else {
                factory = new EliteEnemyFactory();
                enemyAircrafts.add((EnemyAircraft) factory.createAircraft(
                (int) (Math.random() * (Main.WINDOW_WIDTH)),
                (int) (Main.WINDOW_HEIGHT * 0.15),
                random.nextInt(10) - 5,
                random.nextInt(2) + 4, // Setting different speedY based on the type of enemy
                400 + 80 * grow  // Setting different hp based on the type of enemy
            ));
            }
        }
    
        //if(currentScore >= 500 && lastScore < 500) // Changed bossflag to bossflag.get()
        //{
        //    BossAircraftFactory bossfactory = new BossAircraftFactory();
        //    BossAircraft bossAircraft = (BossAircraft) bossfactory.createAircraft(
        //        (int) (Math.random() * (Main.WINDOW_WIDTH)), 
        //        (int) (Math.random() * Main.WINDOW_HEIGHT * 0.02),
        //        2, 0, 5000 + 1000 * grow
        //    );
        //    enemyAircrafts.add(bossAircraft); // Add the created BossAircraft to the list
        //    currentScore = currentScore % 500;
        //   lastScore = currentScore;
        //    Game.set_boss_num(true);
        //}
        //else
        //{
        //    lastScore = currentScore;
        //}
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
