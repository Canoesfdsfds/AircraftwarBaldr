package edu.hitsz.application;


import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.EnemyBulletex1;
import edu.hitsz.bullet.EnemyBulletex2;
import edu.hitsz.bullet.EnemyBulletex3;
import edu.hitsz.bullet.EnemyBulletex4;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.bullet.HeroBulletex1;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.FinalBossAircraft;
import edu.hitsz.aircraft.BossAircraft;
import edu.hitsz.aircraft.Elite2Enemy;
import edu.hitsz.item.BoomItem;
import edu.hitsz.item.HPupItem;
import edu.hitsz.item.PowerUpItem;
import edu.hitsz.item.SpPowerUpItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author canoes
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage BACKGROUND_IMAGE0;
    public static BufferedImage BACKGROUND_IMAGE1;
    public static BufferedImage BACKGROUND_IMAGE2;
    public static BufferedImage BACKGROUND_IMAGE3;
    public static BufferedImage BACKGROUND_IMAGE4;
    public static BufferedImage BACKGROUND_IMAGE5;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE_EX1;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage ELITE2_ENEMY_IMAGE;
    public static BufferedImage HPUP_ITEM_IMAGE;
    public static BufferedImage POWERUP_ITEM_IMAGE;
    public static BufferedImage BOOM_ITEM_IMAGE;
    public static BufferedImage BOSS_IMAGE;
    public static BufferedImage SPPOWERUP_ITEM_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE_EX1;
    public static BufferedImage ENEMY_BULLET_IMAGE_EX2;
    public static BufferedImage ENEMY_BULLET_IMAGE_EX3;
    public static BufferedImage ENEMY_BULLET_IMAGE_EX4;
    public static BufferedImage FINAL_BOSS_IMAGE;

    static {
        try {

            BACKGROUND_IMAGE0 = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
            BACKGROUND_IMAGE1 = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
            BACKGROUND_IMAGE2 = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
            BACKGROUND_IMAGE3 = ImageIO.read(new FileInputStream("src/images/bg4.jpg"));
            BACKGROUND_IMAGE4 = ImageIO.read(new FileInputStream("src/images/bg5.jpg"));
            BACKGROUND_IMAGE5 = ImageIO.read(new FileInputStream("src/images/bg.png"));


            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero.png"));
            HERO_BULLET_IMAGE_EX1 = ImageIO.read(new FileInputStream("src/images/bullet_hero_ex1.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy.png"));
            ENEMY_BULLET_IMAGE_EX1 = ImageIO.read(new FileInputStream("src/images/bullet_ex1.png"));
            ENEMY_BULLET_IMAGE_EX2 = ImageIO.read(new FileInputStream("src/images/bullet_ex2.png"));
            ENEMY_BULLET_IMAGE_EX3 = ImageIO.read(new FileInputStream("src/images/bullet_ex3.png"));
            ENEMY_BULLET_IMAGE_EX4 = ImageIO.read(new FileInputStream("src/images/bullet_ex4.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            ELITE2_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elitePlus.png"));
            HPUP_ITEM_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            POWERUP_ITEM_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bullet.png"));
            BOOM_ITEM_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            BOSS_IMAGE = ImageIO.read(new FileInputStream("src/images/boss.png"));
            SPPOWERUP_ITEM_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bulletPlus.png"));
            FINAL_BOSS_IMAGE = ImageIO.read(new FileInputStream("src/images/finalboss.png"));

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBulletex1.class.getName(), ENEMY_BULLET_IMAGE_EX1);
            CLASSNAME_IMAGE_MAP.put(EnemyBulletex2.class.getName(), ENEMY_BULLET_IMAGE_EX2);
            CLASSNAME_IMAGE_MAP.put(EnemyBulletex3.class.getName(), ENEMY_BULLET_IMAGE_EX3);
            CLASSNAME_IMAGE_MAP.put(EnemyBulletex4.class.getName(), ENEMY_BULLET_IMAGE_EX4);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Elite2Enemy.class.getName(), ELITE2_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HPupItem.class.getName(), HPUP_ITEM_IMAGE);
            CLASSNAME_IMAGE_MAP.put(PowerUpItem.class.getName(), POWERUP_ITEM_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BoomItem.class.getName(), BOOM_ITEM_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossAircraft.class.getName(), BOSS_IMAGE);
            CLASSNAME_IMAGE_MAP.put(SpPowerUpItem.class.getName(), SPPOWERUP_ITEM_IMAGE);
            CLASSNAME_IMAGE_MAP.put(FinalBossAircraft.class.getName(), FINAL_BOSS_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBulletex1.class.getName(), HERO_BULLET_IMAGE_EX1);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
