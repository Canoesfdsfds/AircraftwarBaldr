package edu.hitsz.application;

import edu.hitsz.GUI.EndPanel;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.item.BaseItem;
import edu.hitsz.item.BoomItem;
import edu.hitsz.item.HPupItem;
import edu.hitsz.item.PowerUpItem;
import edu.hitsz.item.SpPowerUpItem;
import edu.hitsz.observers.BombOB;
import edu.hitsz.strategy.FinalBossShootStrategy;
import edu.hitsz.strategy.LastPowerStrategyHero;
import edu.hitsz.strategy.RingShootStrategy;
import edu.hitsz.strategy.RingShootStrategyHero;
import records.GameRecord;
import records.RecordDao;
import records.RecordDaoImpl;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.factory.*;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.*;
import java.text.SimpleDateFormat;

/**
 * 游戏主面板，游戏启动
 *
 * @author canoes
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    public static HeroAircraft heroAircraft;
    private final List<EnemyAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    public static List<BaseBullet> enemyBullets;
    private final List<BaseItem> items; 

    /**
     * 屏幕中出现的敌机最大数量
     */
    private int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    public static int score = 0;
    AtomicBoolean bossflag = new AtomicBoolean(true);
    /**
     * 当前时刻
     */
    private int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;

    private int EliteEnemyshootDuration = 900;
    private int EliteEnemyshootTime = 0;

    private int HeroshootDuration = 150;
    private int HeroEnemyshootTime = 0;

    private int BossshootDuration = 200;
    private int BossEnemyshootTime = 0;
    public static int BossShootIndex = 0;

    private BaseCreateEasy aircraft_factory0 = BaseCreateEasy.getInstance();
    private BaseCreate aircraft_factory1 = BaseCreate.getInstance();
    private BaseCreateHard aircraft_factory2 = BaseCreateHard.getInstance();


    //排行榜用
    public static final Scanner scanner = new Scanner(System.in);
    public static final RecordDao recordDao = new RecordDaoImpl();
    public static final RecordDao recordDaoeasy = new RecordDaoImpl();
    public static final RecordDao recordDaonormal = new RecordDaoImpl();
    public static final String RECORDS_FILE = "game_records.txt";
    public static final int MAX_RECORDS = 20;



    private static int boss_num = 0;
    public static MusicThread bossMusicThread = new MusicThread("src/videos/bgm_boss.wav", true, 1000000, 6, Main.music_en);
    public static MusicThread finalbossMusicThread = new MusicThread("src/videos/上海アリス幻樂団 - 霊知の太陽信仰Nuclear Fusion.wav", true, 1000000, 3, Main.music_en);


    //观察者
    public static BombOB bombOB = new BombOB();


    public static int PowerUpItem_num = 0;
    public static int SpPowerUpItem_num = 0;
    public static int Bomb_num = 0;


    //播放记号
    private static int expindex = -1;
    private static int exptotal = 30;
    public static void setExpindexReady()
    {
        expindex = 0;
    }
    private ImageIcon[] expIcons = {
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE00.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE01.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE02.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE03.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE04.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE05.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE06.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE07.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE08.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE09.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE10.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE11.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE12.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE13.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE14.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE15.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE16.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE17.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE18.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE19.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE20.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE21.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE22.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE23.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE24.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE25.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE26.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE27.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE28.png"),
        new ImageIcon("src/movie/explosion/Giant-Shockwave-02-LOOKAE29.png")
    };

    private static int powerupindex = -1;
    public static void setpowerupindexReady()
    {
        powerupindex = 0;
    }
    private static int poweruptotal = 35;
    private ImageIcon[] powerupIcons = {
        new ImageIcon("src/movie/powerup/powerup100.png"),
        new ImageIcon("src/movie/powerup/powerup101.png"),
        new ImageIcon("src/movie/powerup/powerup102.png"),
        new ImageIcon("src/movie/powerup/powerup103.png"),
        new ImageIcon("src/movie/powerup/powerup104.png"),
        new ImageIcon("src/movie/powerup/powerup105.png"),
        new ImageIcon("src/movie/powerup/powerup106.png"),
        new ImageIcon("src/movie/powerup/powerup107.png"),
        new ImageIcon("src/movie/powerup/powerup108.png"),
        new ImageIcon("src/movie/powerup/powerup109.png"),
        new ImageIcon("src/movie/powerup/powerup110.png"),
        new ImageIcon("src/movie/powerup/powerup111.png"),
        new ImageIcon("src/movie/powerup/powerup112.png"),
        new ImageIcon("src/movie/powerup/powerup113.png"),
        new ImageIcon("src/movie/powerup/powerup114.png"),
        new ImageIcon("src/movie/powerup/powerup115.png"),
        new ImageIcon("src/movie/powerup/powerup116.png"),
        new ImageIcon("src/movie/powerup/powerup117.png"),
        new ImageIcon("src/movie/powerup/powerup118.png"),
        new ImageIcon("src/movie/powerup/powerup119.png"),
        new ImageIcon("src/movie/powerup/powerup120.png"),
        new ImageIcon("src/movie/powerup/powerup121.png"),
        new ImageIcon("src/movie/powerup/powerup122.png"),
        new ImageIcon("src/movie/powerup/powerup123.png"),
        new ImageIcon("src/movie/powerup/powerup124.png"),
        new ImageIcon("src/movie/powerup/powerup125.png"),
        new ImageIcon("src/movie/powerup/powerup126.png"),
        new ImageIcon("src/movie/powerup/powerup127.png"),
        new ImageIcon("src/movie/powerup/powerup128.png"),
        new ImageIcon("src/movie/powerup/powerup129.png"),
        new ImageIcon("src/movie/powerup/powerup130.png"),
        new ImageIcon("src/movie/powerup/powerup131.png"),
        new ImageIcon("src/movie/powerup/powerup132.png"),
        new ImageIcon("src/movie/powerup/powerup133.png"),
        new ImageIcon("src/movie/powerup/powerup134.png")
    };

    private static int superupindex = -1;
    public static void setSuperupindexReady()
    {
        superupindex = 0;
    }
    private static int superuptotal = 35;
    private ImageIcon[] superupIcons = {
        new ImageIcon("src/movie/superup/superup100.png"),
        new ImageIcon("src/movie/superup/superup101.png"),
        new ImageIcon("src/movie/superup/superup102.png"),
        new ImageIcon("src/movie/superup/superup103.png"),
        new ImageIcon("src/movie/superup/superup104.png"),
        new ImageIcon("src/movie/superup/superup105.png"),
        new ImageIcon("src/movie/superup/superup106.png"),
        new ImageIcon("src/movie/superup/superup107.png"),
        new ImageIcon("src/movie/superup/superup108.png"),
        new ImageIcon("src/movie/superup/superup109.png"),
        new ImageIcon("src/movie/superup/superup110.png"),
        new ImageIcon("src/movie/superup/superup111.png"),
        new ImageIcon("src/movie/superup/superup112.png"),
        new ImageIcon("src/movie/superup/superup113.png"),
        new ImageIcon("src/movie/superup/superup114.png"),
        new ImageIcon("src/movie/superup/superup115.png"),
        new ImageIcon("src/movie/superup/superup116.png"),
        new ImageIcon("src/movie/superup/superup117.png"),
        new ImageIcon("src/movie/superup/superup118.png"),
        new ImageIcon("src/movie/superup/superup119.png"),
        new ImageIcon("src/movie/superup/superup120.png"),
        new ImageIcon("src/movie/superup/superup121.png"),
        new ImageIcon("src/movie/superup/superup122.png"),
        new ImageIcon("src/movie/superup/superup123.png"),
        new ImageIcon("src/movie/superup/superup124.png"),
        new ImageIcon("src/movie/superup/superup125.png"),
        new ImageIcon("src/movie/superup/superup126.png"),
        new ImageIcon("src/movie/superup/superup127.png"),
        new ImageIcon("src/movie/superup/superup128.png"),
        new ImageIcon("src/movie/superup/superup129.png"),
        new ImageIcon("src/movie/superup/superup130.png"),
        new ImageIcon("src/movie/superup/superup131.png"),
        new ImageIcon("src/movie/superup/superup132.png"),
        new ImageIcon("src/movie/superup/superup133.png"),
        new ImageIcon("src/movie/superup/superup134.png")
    };

    private static int poweroffindex = -1;
    public static void setPoweroffindexReady()
    {
        poweroffindex = 0;
    }
    private static int powerofftotal = 16;
    private ImageIcon[] poweroffIcons ={
        new ImageIcon("src/movie/poweroff/poweroff00.png"),
        new ImageIcon("src/movie/poweroff/poweroff01.png"),
        new ImageIcon("src/movie/poweroff/poweroff02.png"),
        new ImageIcon("src/movie/poweroff/poweroff03.png"),
        new ImageIcon("src/movie/poweroff/poweroff04.png"),
        new ImageIcon("src/movie/poweroff/poweroff05.png"),
        new ImageIcon("src/movie/poweroff/poweroff06.png"),
        new ImageIcon("src/movie/poweroff/poweroff07.png"),
        new ImageIcon("src/movie/poweroff/poweroff08.png"),
        new ImageIcon("src/movie/poweroff/poweroff09.png"),
        new ImageIcon("src/movie/poweroff/poweroff10.png"),
        new ImageIcon("src/movie/poweroff/poweroff11.png"),
        new ImageIcon("src/movie/poweroff/poweroff12.png"),
        new ImageIcon("src/movie/poweroff/poweroff13.png"),
        new ImageIcon("src/movie/poweroff/poweroff14.png"),
        new ImageIcon("src/movie/poweroff/poweroff15.png"),
    };

    private static int harderindex = -1;
    public static void setHarderindexReady()
    {
        harderindex = 0;
    }
    private static int hardertotal = 25;
    private ImageIcon[] harderIcons = {
        new ImageIcon("src/movie/harder/harder00.png"),
        new ImageIcon("src/movie/harder/harder01.png"),
        new ImageIcon("src/movie/harder/harder02.png"),
        new ImageIcon("src/movie/harder/harder03.png"),
        new ImageIcon("src/movie/harder/harder04.png"),
        new ImageIcon("src/movie/harder/harder05.png"),
        new ImageIcon("src/movie/harder/harder06.png"),
        new ImageIcon("src/movie/harder/harder07.png"),
        new ImageIcon("src/movie/harder/harder08.png"),
        new ImageIcon("src/movie/harder/harder09.png"),
        new ImageIcon("src/movie/harder/harder10.png"),
        new ImageIcon("src/movie/harder/harder11.png"),
        new ImageIcon("src/movie/harder/harder12.png"),
        new ImageIcon("src/movie/harder/harder13.png"),
        new ImageIcon("src/movie/harder/harder14.png"),
        new ImageIcon("src/movie/harder/harder15.png"),
        new ImageIcon("src/movie/harder/harder16.png"),
        new ImageIcon("src/movie/harder/harder17.png"),
        new ImageIcon("src/movie/harder/harder18.png"),
        new ImageIcon("src/movie/harder/harder19.png"),
        new ImageIcon("src/movie/harder/harder20.png"),
        new ImageIcon("src/movie/harder/harder21.png"),
        new ImageIcon("src/movie/harder/harder22.png"),
        new ImageIcon("src/movie/harder/harder23.png"),
        new ImageIcon("src/movie/harder/harder24.png")
    };

    private static int openingindex = -1;
    public static void setOpeningIndexReady()
    {
        openingindex = 0;
    }
    private static int openingtotal = 150;
    private ImageIcon[] openingIcons = {
        new ImageIcon("src/movie/opening/opening000.png"),
        new ImageIcon("src/movie/opening/opening001.png"),
        new ImageIcon("src/movie/opening/opening002.png"),
        new ImageIcon("src/movie/opening/opening003.png"),
        new ImageIcon("src/movie/opening/opening004.png"),
        new ImageIcon("src/movie/opening/opening005.png"),
        new ImageIcon("src/movie/opening/opening006.png"),
        new ImageIcon("src/movie/opening/opening007.png"),
        new ImageIcon("src/movie/opening/opening008.png"),
        new ImageIcon("src/movie/opening/opening009.png"),
        new ImageIcon("src/movie/opening/opening010.png"),
        new ImageIcon("src/movie/opening/opening011.png"),
        new ImageIcon("src/movie/opening/opening012.png"),
        new ImageIcon("src/movie/opening/opening013.png"),
        new ImageIcon("src/movie/opening/opening014.png"),
        new ImageIcon("src/movie/opening/opening015.png"),
        new ImageIcon("src/movie/opening/opening016.png"),
        new ImageIcon("src/movie/opening/opening017.png"),
        new ImageIcon("src/movie/opening/opening018.png"),
        new ImageIcon("src/movie/opening/opening019.png"),
        new ImageIcon("src/movie/opening/opening020.png"),
        new ImageIcon("src/movie/opening/opening021.png"),
        new ImageIcon("src/movie/opening/opening022.png"),
        new ImageIcon("src/movie/opening/opening023.png"),
        new ImageIcon("src/movie/opening/opening024.png"),
        new ImageIcon("src/movie/opening/opening025.png"),
        new ImageIcon("src/movie/opening/opening026.png"),
        new ImageIcon("src/movie/opening/opening027.png"),
        new ImageIcon("src/movie/opening/opening028.png"),
        new ImageIcon("src/movie/opening/opening029.png"),
        new ImageIcon("src/movie/opening/opening030.png"),
        new ImageIcon("src/movie/opening/opening031.png"),
        new ImageIcon("src/movie/opening/opening032.png"),
        new ImageIcon("src/movie/opening/opening033.png"),
        new ImageIcon("src/movie/opening/opening034.png"),
        new ImageIcon("src/movie/opening/opening035.png"),
        new ImageIcon("src/movie/opening/opening036.png"),
        new ImageIcon("src/movie/opening/opening037.png"),
        new ImageIcon("src/movie/opening/opening038.png"),
        new ImageIcon("src/movie/opening/opening039.png"),
        new ImageIcon("src/movie/opening/opening040.png"),
        new ImageIcon("src/movie/opening/opening041.png"),
        new ImageIcon("src/movie/opening/opening042.png"),
        new ImageIcon("src/movie/opening/opening043.png"),
        new ImageIcon("src/movie/opening/opening044.png"),
        new ImageIcon("src/movie/opening/opening045.png"),
        new ImageIcon("src/movie/opening/opening046.png"),
        new ImageIcon("src/movie/opening/opening047.png"),
        new ImageIcon("src/movie/opening/opening048.png"),
        new ImageIcon("src/movie/opening/opening049.png"),
        new ImageIcon("src/movie/opening/opening050.png"),
        new ImageIcon("src/movie/opening/opening051.png"),
        new ImageIcon("src/movie/opening/opening052.png"),
        new ImageIcon("src/movie/opening/opening053.png"),
        new ImageIcon("src/movie/opening/opening054.png"),
        new ImageIcon("src/movie/opening/opening055.png"),
        new ImageIcon("src/movie/opening/opening056.png"),
        new ImageIcon("src/movie/opening/opening057.png"),
        new ImageIcon("src/movie/opening/opening058.png"),
        new ImageIcon("src/movie/opening/opening059.png"),
        new ImageIcon("src/movie/opening/opening060.png"),
        new ImageIcon("src/movie/opening/opening061.png"),
        new ImageIcon("src/movie/opening/opening062.png"),
        new ImageIcon("src/movie/opening/opening063.png"),
        new ImageIcon("src/movie/opening/opening064.png"),
        new ImageIcon("src/movie/opening/opening065.png"),
        new ImageIcon("src/movie/opening/opening066.png"),
        new ImageIcon("src/movie/opening/opening067.png"),
        new ImageIcon("src/movie/opening/opening068.png"),
        new ImageIcon("src/movie/opening/opening069.png"),
        new ImageIcon("src/movie/opening/opening070.png"),
        new ImageIcon("src/movie/opening/opening071.png"),
        new ImageIcon("src/movie/opening/opening072.png"),
        new ImageIcon("src/movie/opening/opening073.png"),
        new ImageIcon("src/movie/opening/opening074.png"),
        new ImageIcon("src/movie/opening/opening075.png"),
        new ImageIcon("src/movie/opening/opening076.png"),
        new ImageIcon("src/movie/opening/opening077.png"),
        new ImageIcon("src/movie/opening/opening078.png"),
        new ImageIcon("src/movie/opening/opening079.png"),
        new ImageIcon("src/movie/opening/opening080.png"),
        new ImageIcon("src/movie/opening/opening081.png"),
        new ImageIcon("src/movie/opening/opening082.png"),
        new ImageIcon("src/movie/opening/opening083.png"),
        new ImageIcon("src/movie/opening/opening084.png"),
        new ImageIcon("src/movie/opening/opening085.png"),
        new ImageIcon("src/movie/opening/opening086.png"),
        new ImageIcon("src/movie/opening/opening087.png"),
        new ImageIcon("src/movie/opening/opening088.png"),
        new ImageIcon("src/movie/opening/opening089.png"),
        new ImageIcon("src/movie/opening/opening090.png"),
        new ImageIcon("src/movie/opening/opening091.png"),
        new ImageIcon("src/movie/opening/opening092.png"),
        new ImageIcon("src/movie/opening/opening093.png"),
        new ImageIcon("src/movie/opening/opening094.png"),
        new ImageIcon("src/movie/opening/opening095.png"),
        new ImageIcon("src/movie/opening/opening096.png"),
        new ImageIcon("src/movie/opening/opening097.png"),
        new ImageIcon("src/movie/opening/opening098.png"),
        new ImageIcon("src/movie/opening/opening099.png"),
        new ImageIcon("src/movie/opening/opening100.png"),
        new ImageIcon("src/movie/opening/opening101.png"),
        new ImageIcon("src/movie/opening/opening102.png"),
        new ImageIcon("src/movie/opening/opening103.png"),
        new ImageIcon("src/movie/opening/opening104.png"),
        new ImageIcon("src/movie/opening/opening105.png"),
        new ImageIcon("src/movie/opening/opening106.png"),
        new ImageIcon("src/movie/opening/opening107.png"),
        new ImageIcon("src/movie/opening/opening108.png"),
        new ImageIcon("src/movie/opening/opening109.png"),
        new ImageIcon("src/movie/opening/opening100.png"),
        new ImageIcon("src/movie/opening/opening111.png"),
        new ImageIcon("src/movie/opening/opening112.png"),
        new ImageIcon("src/movie/opening/opening113.png"),
        new ImageIcon("src/movie/opening/opening114.png"),
        new ImageIcon("src/movie/opening/opening115.png"),
        new ImageIcon("src/movie/opening/opening116.png"),
        new ImageIcon("src/movie/opening/opening117.png"),
        new ImageIcon("src/movie/opening/opening118.png"),
        new ImageIcon("src/movie/opening/opening119.png"),
        new ImageIcon("src/movie/opening/opening120.png"),
        new ImageIcon("src/movie/opening/opening121.png"),
        new ImageIcon("src/movie/opening/opening122.png"),
        new ImageIcon("src/movie/opening/opening123.png"),
        new ImageIcon("src/movie/opening/opening124.png"),
        new ImageIcon("src/movie/opening/opening125.png"),
        new ImageIcon("src/movie/opening/opening126.png"),
        new ImageIcon("src/movie/opening/opening127.png"),
        new ImageIcon("src/movie/opening/opening128.png"),
        new ImageIcon("src/movie/opening/opening129.png"),
        new ImageIcon("src/movie/opening/opening130.png"),
        new ImageIcon("src/movie/opening/opening131.png"),
        new ImageIcon("src/movie/opening/opening132.png"),
        new ImageIcon("src/movie/opening/opening133.png"),
        new ImageIcon("src/movie/opening/opening134.png"),
        new ImageIcon("src/movie/opening/opening135.png"),
        new ImageIcon("src/movie/opening/opening136.png"),
        new ImageIcon("src/movie/opening/opening137.png"),
        new ImageIcon("src/movie/opening/opening138.png"),
        new ImageIcon("src/movie/opening/opening139.png"),
        new ImageIcon("src/movie/opening/opening140.png"),
        new ImageIcon("src/movie/opening/opening141.png"),
        new ImageIcon("src/movie/opening/opening142.png"),
        new ImageIcon("src/movie/opening/opening143.png"),
        new ImageIcon("src/movie/opening/opening144.png"),
        new ImageIcon("src/movie/opening/opening145.png"),
        new ImageIcon("src/movie/opening/opening146.png"),
        new ImageIcon("src/movie/opening/opening147.png"),
        new ImageIcon("src/movie/opening/opening148.png"),
        new ImageIcon("src/movie/opening/opening149.png")
    };

    private static int lossindex = -1;
    public void setLossIndexReady()
    {
        lossindex = 0;
    }
    private static int losstotal = 50;
    private ImageIcon[] lossIcons = {
        new ImageIcon("src/movie/lose/loss00.png"),
        new ImageIcon("src/movie/lose/loss01.png"),
        new ImageIcon("src/movie/lose/loss02.png"),
        new ImageIcon("src/movie/lose/loss03.png"),
        new ImageIcon("src/movie/lose/loss04.png"),
        new ImageIcon("src/movie/lose/loss05.png"),
        new ImageIcon("src/movie/lose/loss06.png"),
        new ImageIcon("src/movie/lose/loss07.png"),
        new ImageIcon("src/movie/lose/loss08.png"),
        new ImageIcon("src/movie/lose/loss09.png"),
        new ImageIcon("src/movie/lose/loss10.png"),
        new ImageIcon("src/movie/lose/loss11.png"),
        new ImageIcon("src/movie/lose/loss12.png"),
        new ImageIcon("src/movie/lose/loss13.png"),
        new ImageIcon("src/movie/lose/loss14.png"),
        new ImageIcon("src/movie/lose/loss15.png"),
        new ImageIcon("src/movie/lose/loss16.png"),
        new ImageIcon("src/movie/lose/loss17.png"),
        new ImageIcon("src/movie/lose/loss18.png"),
        new ImageIcon("src/movie/lose/loss19.png"),
        new ImageIcon("src/movie/lose/loss20.png"),
        new ImageIcon("src/movie/lose/loss21.png"),
        new ImageIcon("src/movie/lose/loss22.png"),
        new ImageIcon("src/movie/lose/loss23.png"),
        new ImageIcon("src/movie/lose/loss24.png"),
        new ImageIcon("src/movie/lose/loss25.png"),
        new ImageIcon("src/movie/lose/loss26.png"),
        new ImageIcon("src/movie/lose/loss27.png"),
        new ImageIcon("src/movie/lose/loss28.png"),
        new ImageIcon("src/movie/lose/loss29.png"),
        new ImageIcon("src/movie/lose/loss30.png"),
        new ImageIcon("src/movie/lose/loss31.png"),
        new ImageIcon("src/movie/lose/loss32.png"),
        new ImageIcon("src/movie/lose/loss33.png"),
        new ImageIcon("src/movie/lose/loss34.png"),
        new ImageIcon("src/movie/lose/loss35.png"),
        new ImageIcon("src/movie/lose/loss36.png"),
        new ImageIcon("src/movie/lose/loss37.png"),
        new ImageIcon("src/movie/lose/loss38.png"),
        new ImageIcon("src/movie/lose/loss39.png"),
        new ImageIcon("src/movie/lose/loss40.png"),
        new ImageIcon("src/movie/lose/loss41.png"),
        new ImageIcon("src/movie/lose/loss42.png"),
        new ImageIcon("src/movie/lose/loss43.png"),
        new ImageIcon("src/movie/lose/loss44.png"),
        new ImageIcon("src/movie/lose/loss45.png"),
        new ImageIcon("src/movie/lose/loss46.png"),
        new ImageIcon("src/movie/lose/loss47.png"),
        new ImageIcon("src/movie/lose/loss48.png"),
        new ImageIcon("src/movie/lose/loss49.png"),
    };

    private static int teindex = -1;
    public void setTeIndexReady()
    {
        teindex = 0;
    }
    private static int tetotal = 50;
    private ImageIcon[] teIcons = {
        new ImageIcon("src/movie/te/te00.png"),
        new ImageIcon("src/movie/te/te01.png"),
        new ImageIcon("src/movie/te/te02.png"),
        new ImageIcon("src/movie/te/te03.png"),
        new ImageIcon("src/movie/te/te04.png"),
        new ImageIcon("src/movie/te/te05.png"),
        new ImageIcon("src/movie/te/te06.png"),
        new ImageIcon("src/movie/te/te07.png"),
        new ImageIcon("src/movie/te/te08.png"),
        new ImageIcon("src/movie/te/te09.png"),
        new ImageIcon("src/movie/te/te10.png"),
        new ImageIcon("src/movie/te/te11.png"),
        new ImageIcon("src/movie/te/te12.png"),
        new ImageIcon("src/movie/te/te13.png"),
        new ImageIcon("src/movie/te/te14.png"),
        new ImageIcon("src/movie/te/te15.png"),
        new ImageIcon("src/movie/te/te16.png"),
        new ImageIcon("src/movie/te/te17.png"),
        new ImageIcon("src/movie/te/te18.png"),
        new ImageIcon("src/movie/te/te19.png"),
        new ImageIcon("src/movie/te/te20.png"),
        new ImageIcon("src/movie/te/te21.png"),
        new ImageIcon("src/movie/te/te22.png"),
        new ImageIcon("src/movie/te/te23.png"),
        new ImageIcon("src/movie/te/te24.png"),
        new ImageIcon("src/movie/te/te25.png"),
        new ImageIcon("src/movie/te/te26.png"),
        new ImageIcon("src/movie/te/te27.png"),
        new ImageIcon("src/movie/te/te28.png"),
        new ImageIcon("src/movie/te/te29.png"),
        new ImageIcon("src/movie/te/te30.png"),
        new ImageIcon("src/movie/te/te31.png"),
        new ImageIcon("src/movie/te/te32.png"),
        new ImageIcon("src/movie/te/te33.png"),
        new ImageIcon("src/movie/te/te34.png"),
        new ImageIcon("src/movie/te/te35.png"),
        new ImageIcon("src/movie/te/te36.png"),
        new ImageIcon("src/movie/te/te37.png"),
        new ImageIcon("src/movie/te/te38.png"),
        new ImageIcon("src/movie/te/te39.png"),
        new ImageIcon("src/movie/te/te40.png"),
        new ImageIcon("src/movie/te/te41.png"),
        new ImageIcon("src/movie/te/te42.png"),
        new ImageIcon("src/movie/te/te43.png"),
        new ImageIcon("src/movie/te/te44.png"),
        new ImageIcon("src/movie/te/te45.png"),
        new ImageIcon("src/movie/te/te46.png"),
        new ImageIcon("src/movie/te/te47.png"),
        new ImageIcon("src/movie/te/te48.png"),
        new ImageIcon("src/movie/te/te49.png")
    };

    private static int moveonindex = -1;
    public void setMoveonIndexReady()
    {
        moveonindex = 0;
    }
    private static int moveontotal = 50;
    private ImageIcon[] moveonIcons = {
        new ImageIcon("src/movie/moveon/moveon00.png"),
        new ImageIcon("src/movie/moveon/moveon01.png"),
        new ImageIcon("src/movie/moveon/moveon02.png"),
        new ImageIcon("src/movie/moveon/moveon03.png"),
        new ImageIcon("src/movie/moveon/moveon04.png"),
        new ImageIcon("src/movie/moveon/moveon05.png"),
        new ImageIcon("src/movie/moveon/moveon06.png"),
        new ImageIcon("src/movie/moveon/moveon07.png"),
        new ImageIcon("src/movie/moveon/moveon08.png"),
        new ImageIcon("src/movie/moveon/moveon09.png"),
        new ImageIcon("src/movie/moveon/moveon10.png"),
        new ImageIcon("src/movie/moveon/moveon11.png"),
        new ImageIcon("src/movie/moveon/moveon12.png"),
        new ImageIcon("src/movie/moveon/moveon13.png"),
        new ImageIcon("src/movie/moveon/moveon14.png"),
        new ImageIcon("src/movie/moveon/moveon15.png"),
        new ImageIcon("src/movie/moveon/moveon16.png"),
        new ImageIcon("src/movie/moveon/moveon17.png"),
        new ImageIcon("src/movie/moveon/moveon18.png"),
        new ImageIcon("src/movie/moveon/moveon19.png"),
        new ImageIcon("src/movie/moveon/moveon20.png"),
        new ImageIcon("src/movie/moveon/moveon21.png"),
        new ImageIcon("src/movie/moveon/moveon22.png"),
        new ImageIcon("src/movie/moveon/moveon23.png"),
        new ImageIcon("src/movie/moveon/moveon24.png"),
        new ImageIcon("src/movie/moveon/moveon25.png"),
        new ImageIcon("src/movie/moveon/moveon26.png"),
        new ImageIcon("src/movie/moveon/moveon27.png"),
        new ImageIcon("src/movie/moveon/moveon28.png"),
        new ImageIcon("src/movie/moveon/moveon29.png"),
        new ImageIcon("src/movie/moveon/moveon30.png"),
        new ImageIcon("src/movie/moveon/moveon31.png"),
        new ImageIcon("src/movie/moveon/moveon32.png"),
        new ImageIcon("src/movie/moveon/moveon33.png"),
        new ImageIcon("src/movie/moveon/moveon34.png"),
        new ImageIcon("src/movie/moveon/moveon35.png"),
        new ImageIcon("src/movie/moveon/moveon36.png"),
        new ImageIcon("src/movie/moveon/moveon37.png"),
        new ImageIcon("src/movie/moveon/moveon38.png"),
        new ImageIcon("src/movie/moveon/moveon39.png"),
        new ImageIcon("src/movie/moveon/moveon40.png"),
        new ImageIcon("src/movie/moveon/moveon41.png"),
        new ImageIcon("src/movie/moveon/moveon42.png"),
        new ImageIcon("src/movie/moveon/moveon43.png"),
        new ImageIcon("src/movie/moveon/moveon44.png"),
        new ImageIcon("src/movie/moveon/moveon45.png"),
        new ImageIcon("src/movie/moveon/moveon46.png"),
        new ImageIcon("src/movie/moveon/moveon47.png"),
        new ImageIcon("src/movie/moveon/moveon48.png"),
        new ImageIcon("src/movie/moveon/moveon49.png")
    };

    private boolean loop_power_up = false;


    private MusicThread bgmThread;
    private MusicThread lastMusic;




    private static boolean finalstage = false;
    private static boolean hasfinalstage = false;
    public static void setFinal()
    {
        finalstage = true;
    }
    private FinalBossAircraft finalBossAircraft;


    public static int FinalBossShootIndex = 0;
    public static int FinalBossShootCount = 0;


    public static int HeroShooIndex = 0;


    public static int waitIndex = 0;




    /**
     * 游戏结束标志
     */
    private static boolean gameOverFlag = false;
    public static boolean IfVictory()
    {
        return !gameOverFlag;
    }

    public Game() {
        if(Main.mode == 0)
        heroAircraft = aircraft_factory0.CreateHero(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(), 0, 0, 1000);
        else if(Main.mode == 1)
        heroAircraft = aircraft_factory1.CreateHero(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(), 0, 0, 500);
        else if(Main.mode == 2)
        heroAircraft = aircraft_factory2.CreateHero(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(), 0, 0, 200);
        enemyAircrafts = new LinkedList<EnemyAircraft>();
        heroBullets = new LinkedList<BaseBullet>();
        enemyBullets = new LinkedList<BaseBullet>();
        items = new LinkedList<BaseItem>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);
        HeroKeyController heroKeyController = new HeroKeyController(this, heroAircraft, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

        //键盘监听
        Main.frame.addKeyListener(heroKeyController);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        setOpeningIndexReady();//播放开场动画
        if(Main.mode == 0)
        bgmThread = new MusicThread("src/videos/上海アリス幻樂団 - 神々が恋した幻想郷.wav", true, 100000000, -6, Main.music_en);
        else if(Main.mode == 1)
        bgmThread = new MusicThread("src/videos/上海アリス幻樂団 - 輝く針の小人族 Little Princess.wav", true, 100000000, -4, Main.music_en);
        else if(Main.mode == 2)
        bgmThread = new MusicThread("src/videos/上海アリス幻樂団 - エクステンドアッシュ蓬莱人.wav", true, 100000000, -4, Main.music_en);
        bgmThread.start();

        //heroAircraft.setShootStrategy(new RingShootStrategyHero());
        //heroAircraft.setShootStrategy(new LastPowerStrategyHero());
        //HeroshootDuration = 100;


        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            //System.out.println(time);

            if(finalstage && !hasfinalstage)
            {
                IntoFinalpart1();
            }


            if(openingindex > 132)//播放开场动画时停止其他动作
            {
            // 周期性执行（控制频率）
            if(! finalstage){//最终阶段不自动生成敌机
                if (timeCountAndNewCycleJudge()) {
                    //System.out.println(time);
                    // 新敌机产生
                    if(Main.mode == 0)
                    aircraft_factory0.CreateEnemy(enemyAircrafts, enemyMaxNumber, bossflag, score, time);
                    else if(Main.mode == 1)
                    aircraft_factory1.CreateEnemy(enemyAircrafts, enemyMaxNumber, bossflag, score, time);
                    else if(Main.mode == 2)
                    aircraft_factory2.CreateEnemy(enemyAircrafts, enemyMaxNumber, bossflag, score, time);
                }
            }
            else
            {
                if(waitIndex < 251){
                    waitIndex++;
                    if(waitIndex == 250)
                    {
                        IntoFinalpart2();
                    }
                }
            }


            if(timeCountAndNewCycleJudge_for_EliteEnemyshoot())
            {
                // 精英敌机飞机射出子弹
                EliteEnemy_shootAction();
            }
            if(timeCountAndNewCycleJudge_for_BossEnemyshoot())
            {
                //Boss发射子弹
                for(AbstractAircraft abstractAircraft : enemyAircrafts)
                {
                    if(abstractAircraft instanceof BossAircraft)
                    {
                        abstractAircraft.shoot(abstractAircraft, 50);
                        BossShootIndex++;
                    }
                    else if(abstractAircraft instanceof FinalBossAircraft)
                    {
                        abstractAircraft.shoot(abstractAircraft, 50);
                        FinalBossShootIndex++;
                        //System.out.println(FinalBossShootIndex);
                        //System.out.println(FinalBossShootCount);
                        if(FinalBossShootIndex % 10 == 0)
                        {
                            FinalBossShootCount++;
                        }
                    }
                }
            }
            if(timeCountAndNewCycleJudge_for_Heroshoot())
            {
                //自机射出子弹
                HeroAircraft_shootAction();
                HeroShooIndex++;
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            itemsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();
            }

            //每个时刻重绘界面
            repaint();


            //胜利判定
            if(teindex == tetotal - 1){
                executorService.shutdown();
                // Print leaderboard
                EndPanel.main(null);
            }

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                if(!finalstage){
                    // 游戏结束
                    if(lossindex == -1){
                        MusicThread gameovermusic = new MusicThread("src/videos/game_over.wav", false, 3000, 0, Main.music_en);
                        gameovermusic.start();
                        gameOverFlag = true;
                        bossMusicThread.stopPlayback();
                        finalbossMusicThread.stopPlayback();
                        bgmThread.stopPlayback();
                        setLossIndexReady();//播放失败动画
                    }
                    if(lossindex == losstotal - 1){
                        executorService.shutdown();
                        // Print leaderboard
                        EndPanel.main(null);
                    }
                }
                else
                {
                    MoveOn();//觉醒
                }
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {//总局频率控制
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private boolean timeCountAndNewCycleJudge_for_EliteEnemyshoot() {//精英敌机子弹频率控制
        EliteEnemyshootTime += timeInterval;
        if (EliteEnemyshootTime >= EliteEnemyshootDuration && EliteEnemyshootTime - timeInterval < EliteEnemyshootTime) {
            // 跨越到新的周期
            EliteEnemyshootTime %= EliteEnemyshootDuration;
            return true;
        } else {
            return false;
        }
    }

    private boolean timeCountAndNewCycleJudge_for_BossEnemyshoot() {//BOSS敌机子弹频率控制
        BossEnemyshootTime += timeInterval;
        if (BossEnemyshootTime >= BossshootDuration && BossEnemyshootTime - timeInterval < BossEnemyshootTime) {
            // 跨越到新的周期
            BossEnemyshootTime %= BossshootDuration;
            return true;
        } else {
            return false;
        }
    }

    private boolean timeCountAndNewCycleJudge_for_Heroshoot() {//自机子弹频率控制
        HeroEnemyshootTime += timeInterval;
        if (HeroEnemyshootTime >= HeroshootDuration && HeroEnemyshootTime - timeInterval < HeroEnemyshootTime) {
            // 跨越到新的周期
            HeroEnemyshootTime %= HeroshootDuration;
            return true;
        } else {
            return false;
        }
    }

    private void EliteEnemy_shootAction() {
        // TODO 精英敌机射击
        for (AbstractAircraft abstractAircraft : enemyAircrafts) {
            if (!(abstractAircraft instanceof MobEnemy)){
                if(!(abstractAircraft instanceof BossAircraft))
                enemyBullets.addAll(abstractAircraft.shoot(abstractAircraft, 30));
                abstractAircraft.shoot(abstractAircraft, 30);
            }
        }
    }
    private void HeroAircraft_shootAction(){
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot(heroAircraft, 50));
        MusicThread shootmusic = new MusicThread("src/videos/single_bullet.wav", false, 10000, -15.0f, Main.music_en);
        shootmusic.start();
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void itemsMoveAction(){
        for(BaseItem item : items){
            item.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for(BaseBullet bullet : enemyBullets){
            if(bullet.notValid())
            {
                continue;
            }
            if(heroAircraft.crash(bullet))
            // 自机撞击到英雄机子弹
            // 自机损失一定生命值
            {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }


        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    MusicThread shoot_hit_Thread = new MusicThread("src/videos/bullet_hit.wav", false, 10000, -8, Main.music_en);
                    shoot_hit_Thread.start();
                    enemyAircraft.decreaseHp(bullet.getPower());
                    //System.out.println("hit");
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        //score += 10;
                        //System.out.println("Vanish");
                        if(! (enemyAircraft instanceof MobEnemy))
                        {
                            double ran = Math.random();
                            //System.out.println(ran);
                            if(enemyAircraft instanceof EliteEnemy){
                                if(Main.mode == 2)
                                {
                                    if(ran < 0.8){
                                        items.addAll(((EliteEnemy)enemyAircraft).fall());
                                    }
                                }
                                else
                                {
                                    items.addAll(((EliteEnemy)enemyAircraft).fall());
                                }
                            }
                            else if (enemyAircraft instanceof Elite2Enemy){
                                if(Main.mode == 2)
                                {
                                    if(ran < 0.8){
                                        items.addAll(((Elite2Enemy)enemyAircraft).fall());
                                    }
                                }
                                else
                                {
                                    items.addAll(((Elite2Enemy)enemyAircraft).fall());
                                }
                            }
                            else if (enemyAircraft instanceof BossAircraft)
                            { 
                                set_boss_num(false);
                                for(int i = 0 ; i < (Main.mode == 2 ? 2 : 3) ; i++)
                                {
                                    items.addAll(((BossAircraft)enemyAircraft).fall());
                                }
                            }
                            else if (enemyAircraft instanceof FinalBossAircraft) {
                                // 击坠最后BOSS游戏结束
                                if(teindex == -1){
                                    MusicThread gameovermusic = new MusicThread("src/videos/victory.wav", false, 3000, 3, Main.music_en);
                                    gameovermusic.start();
                                    bossMusicThread.stopPlayback();
                                    finalbossMusicThread.stopPlayback();
                                    bgmThread.stopPlayback();
                                    lastMusic.stopPlayback();
                                    setTeIndexReady();//播放胜利动画
                                }
                            }
                        }
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for(BaseItem item : items)
        {
            if(item.notValid()){
                continue;
            }
            if(item.crash(heroAircraft)||heroAircraft.crash(item)){
                if(item instanceof HPupItem)
                {
                    MusicThread supplyMusicThread0 = new MusicThread("src/videos/get_supply.wav", false, 3000, 0, Main.music_en);
                    supplyMusicThread0.start();
                    item.make_influence(heroAircraft, 50);
                }
                if(item instanceof PowerUpItem)
                {
                    heroAircraft.Powerup(30);
                    MusicThread supplyMusicThread1 = new MusicThread("src/videos/get_supply.wav", false, 3000, 0, Main.music_en);
                    supplyMusicThread1.start();
                    PowerUpItem_num++;
                }
                if(item instanceof SpPowerUpItem)
                {
                    heroAircraft.Powerup(60);
                    MusicThread supplyMusicThread2 = new MusicThread("src/videos/get_supply.wav", false, 3000, 0, Main.music_en);
                    supplyMusicThread2.start();
                    SpPowerUpItem_num++;
                }
                if(item instanceof BoomItem)
                {
                    MusicThread supplyMusicThread3 = new MusicThread("src/videos/get_supply.wav", false, 3000, 0, Main.music_en);
                    supplyMusicThread3.start();
                    Bomb_num++;
                }
                item.vanish();
            }
        }
    }







    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        items.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(openingindex >= 132){
        // 绘制背景,图片滚动
        if(Main.mode == 0)
        {
            g.drawImage(ImageManager.BACKGROUND_IMAGE1, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
            g.drawImage(ImageManager.BACKGROUND_IMAGE1, 0, this.backGroundTop, null);
        }
        if(Main.mode == 1)
        {
            g.drawImage(ImageManager.BACKGROUND_IMAGE4, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
            g.drawImage(ImageManager.BACKGROUND_IMAGE4, 0, this.backGroundTop, null);
        }
        if(Main.mode == 2)
        {
            g.drawImage(ImageManager.BACKGROUND_IMAGE5, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
            g.drawImage(ImageManager.BACKGROUND_IMAGE5, 0, this.backGroundTop, null);
        }
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, items);

        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - heroAircraft.getWidth() / 2,
                heroAircraft.getLocationY() - heroAircraft.getHeight() / 2, heroAircraft.getWidth(), heroAircraft.getHeight(), null);
        //绘制得分和生命值
        paintScoreAndLife(g);

        //绘制道具
        try {
            paintUsableItems(g, PowerUpItem_num, SpPowerUpItem_num, Bomb_num);
            //painttest(g, heroAircraft);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //绘制动画

        if(expindex != -1)
        {
            playImageSequence_explosion(g, expIcons, -450, 0);
        }

        if(powerupindex != -1)
        {
            playImageSequence_powerup(g, powerupIcons, heroAircraft);
        }

        if(superupindex != -1)
        {
            playImageSequence_superup(g, superupIcons, heroAircraft);
        }
        if(!loop_power_up){
            if(poweroffindex != -1)
            {
                playImageSequence_poweroff(g, poweroffIcons, heroAircraft);
            }
        }

        if(harderindex != -1)
        {
            playImageSequence_harder(g, harderIcons);
        }

        if(lossindex != -1)
        {
            playImageSequence_lose(g, lossIcons);
        }

        if(teindex != -1)
        {
            playImageSequence_te(g, teIcons);
        }

        if(moveonindex != -1)
        {
            playImageSequence_moveon(g, moveonIcons);
        }

        if(loop_power_up)
        {
            playImageSequence_powerup_loop(g, powerupIcons, heroAircraft);
        }
    }
        //播放开头动画
        if(openingindex != -1 && openingindex < openingtotal)
        {
            playImageSequence_opening(g, openingIcons);
        }

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + Game.score, x, y);
        if(Main.mode == 0)
        {
            g.setColor(new Color(0x48fc9d));
            g.drawString("EASY", 425, y);
        }
        if(Main.mode == 1)
        {
            g.setColor(new Color(0xdff242));
            g.drawString("NORMAL", 395, y);
        }
        if(Main.mode == 2)
        {
            g.setColor(new Color(0x5c1417));
            g.drawString("HARD", 425, y);
        }
        g.setColor(new Color(16711680));
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    private void paintUsableItems(Graphics g, int powerUpItem_num, int SpPowerUpItem_num, int bomb_num) throws IOException{
        BufferedImage powerUpBufferedImage = ImageIO.read(new File("src/images/prop_bullet.png"));
        BufferedImage SpPowerUpBufferedImage = ImageIO.read(new File("src/images/prop_bulletPlus.png"));
        BufferedImage bombBufferedImage = ImageIO.read(new File("src/images/prop_bomb.png"));
        for(int i = 0 ; i < powerUpItem_num ; i++)
        {g.drawImage(powerUpBufferedImage, 45+30*i, 62, powerUpBufferedImage.getWidth()*2/3, powerUpBufferedImage.getHeight()*2/3,null);}
        for(int i = 0 ; i < SpPowerUpItem_num ; i++)
        {g.drawImage(SpPowerUpBufferedImage, 38+30*i, 95, SpPowerUpBufferedImage.getWidth()*2/3, SpPowerUpBufferedImage.getHeight()*2/3,null);}
        for(int i = 0 ; i < bomb_num ; i++)
        {g.drawImage(bombBufferedImage, 38+30*i, 128, bombBufferedImage.getWidth(), bombBufferedImage.getHeight(),null);}
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("C:", 15, 80);
        g.drawString("V:", 15, 115);
        g.drawString("B:", 15, 150);
    }

    private void painttest(Graphics g, HeroAircraft heroAircraft) throws IOException{
        //BufferedImage image = ImageIO.read(new File("src/images/prop_bullet.png"));
        System.out.println("Testing");
        g.drawImage(harderIcons[8].getImage(),0,0,this);
        //g.drawImage(powerupIcons[8].getImage(),200,200,this);
    }
    



    private void playImageSequence_explosion(Graphics g, ImageIcon[] images, int x, int y) {
        if (expindex == exptotal) {
            expindex = -1;
            return;
        } else {
            BufferedImage image = new BufferedImage(images[expindex].getIconWidth(), images[expindex].getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) image.getGraphics();
            g2d.rotate(-Math.PI / 2, images[expindex].getIconWidth() / 2, images[expindex].getIconHeight() / 2);
            g2d.drawImage(images[expindex].getImage(), 0, 0, this);
            g2d.dispose();
    
            g.drawImage(image, x, y, this);
            expindex++;
        }
    }

    private void playImageSequence_powerup(Graphics g, ImageIcon[] images, HeroAircraft heroAircraft) {
        if (powerupindex == poweruptotal) {
            powerupindex = -1;
            return;
        } else {
            g.drawImage(images[powerupindex].getImage(), heroAircraft.getLocationX()-31,heroAircraft.getLocationY()-25, 63, 50, null);
            powerupindex++;
        }
    }

    private void playImageSequence_powerup_loop(Graphics g, ImageIcon[] images, HeroAircraft heroAircraft) {
        if (powerupindex == poweruptotal) {
            powerupindex = 0;
            return;
        } else {
            g.drawImage(images[powerupindex].getImage(), heroAircraft.getLocationX()-31,heroAircraft.getLocationY()-25, 63, 50, null);
            powerupindex++;
        }
    }

    private void playImageSequence_superup(Graphics g, ImageIcon[] images, HeroAircraft heroAircraft) {
        if (superupindex == superuptotal) {
            superupindex = -1;
            return;
        } else {
            g.drawImage(images[superupindex].getImage(), heroAircraft.getLocationX()-31,heroAircraft.getLocationY()-25, 63, 50, null);
            superupindex++;
        }
    }

    private void playImageSequence_poweroff(Graphics g, ImageIcon[] images, HeroAircraft heroAircraft) {
        if (poweroffindex == powerofftotal) {
            poweroffindex = -1;
            return;
        } else {
            g.drawImage(images[poweroffindex].getImage(), heroAircraft.getLocationX()-31,heroAircraft.getLocationY()-25, 63, 50, null);
            poweroffindex++;
        }
    }

    private void playImageSequence_harder(Graphics g, ImageIcon[] images) {
        if (harderindex == hardertotal) {
            harderindex = -1;
            return;
        } else {
            g.drawImage(images[harderindex].getImage(), 0,0, this);
            harderindex++;
        }
    }

    private void playImageSequence_opening(Graphics g, ImageIcon[] images){
        g.drawImage(images[openingindex].getImage(), 0, 0, this);
        openingindex++;
    }

    private void playImageSequence_lose(Graphics g, ImageIcon[] images){
        if(lossindex >= losstotal){return;}
        g.drawImage(images[lossindex].getImage(), 0, 0, this);
        lossindex++;
    }

    private void playImageSequence_te(Graphics g, ImageIcon[] images){
        if(teindex >= tetotal){return;}
        g.drawImage(images[teindex].getImage(), 0, 0, this);
        teindex++;
    }

    private void playImageSequence_moveon(Graphics g, ImageIcon[] images){
        if(moveonindex >= moveontotal){return;}
        g.drawImage(images[moveonindex].getImage(), 0, 0, this);
        moveonindex++;
    }


    //排行榜部分
    private static void loadRecordsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String gameName = parts[0].trim();
                    int gameScore = Integer.parseInt(parts[1].trim());
                    String gameDate = parts[2].trim();
                    GameRecord record = new GameRecord(gameName, gameScore, gameDate);
                    recordDao.doAdd(record);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading records from file: " + e.getMessage());
        }
    }

    private static void simulateEndOfGame(int score) {
        // Simulate end of the game
        System.out.println("Game Over!");
        //System.out.print("Enter your score: ");
        //int score = scanner.nextInt();
        //scanner.nextLine(); // Consume newline
        // Prompt user to enter game name
        System.out.print("Enter your game name: ");
        String gameName = scanner.nextLine().trim();
        // Get current system time
        String gameDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // Create a game record object
        GameRecord gameRecord = new GameRecord(gameName, score, gameDate);
        // Add the record to DAO
        recordDao.doAdd(gameRecord);
    }

    private static void saveGameRecord() {
        // Get the latest records
        List<GameRecord> allRecords = recordDao.Get_All_Records();
        // Sort the records by score descending
        allRecords.sort(Comparator.comparingInt(GameRecord::getGameScore).reversed());
        // Truncate the list to maximum records
        if (allRecords.size() > MAX_RECORDS) {
            allRecords = allRecords.subList(0, MAX_RECORDS);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_FILE))) {
            for (GameRecord record : allRecords) {
                writer.write(record.getGameName() + ", " + record.getGameScore() + ", " + record.getGameDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving records to file: " + e.getMessage());
        }
    }

    private static void printLeaderboard() {
        System.out.println("Leaderboard:");
        List<GameRecord> allRecords = recordDao.Get_All_Records();
        if (allRecords.isEmpty()) {
            System.out.println("No records found.");
        } else {
            int rank = 1;
            for (GameRecord record : allRecords) {
                System.out.println(rank + ". " + record.getGameName() + " - Score: " + record.getGameScore() + ", Date: " + record.getGameDate());
                rank++;
            }
        }
    }

    public static void set_boss_num(boolean add)
    {
        if(add)
        {
            boss_num++;
            boss_music_control();
        }
        else
        {
            boss_num--;
            boss_music_control();
        }
        return;
    }

    private static void boss_music_control() {
        if (boss_num > 0) {
            if (bossMusicThread == null || !bossMusicThread.isAlive()) {
                bossMusicThread = new MusicThread("src/videos/Peak A Soul+ - 成敗!.wav", true, 1000000, 0, Main.music_en);
                bossMusicThread.start();
                System.out.println("Start playing boss music");
            }
        } else {
            if (bossMusicThread != null && bossMusicThread.isAlive()) {
                bossMusicThread.stopPlayback();
                System.out.println("Stop playing boss music");
            }
        }
    }



    //最终阶段
    public void IntoFinalpart1()
    {
        //进入最终阶段
        hasfinalstage = true;
        MusicThread bombMusicThread = new MusicThread("src/videos/bomb_explosion.wav", false, 20000, -2, Main.music_en);
        bombMusicThread.start();
        setExpindexReady();
        enemyAircrafts.clear();
        enemyBullets.clear();
        bgmThread.stopPlayback();
        bossMusicThread.stopPlayback();
        MusicThread warningMusicThread = new MusicThread("src/videos/warning.wav",false, 4000, 0, Main.music_en);
        warningMusicThread.start();
    }

    public void IntoFinalpart2()
    {
        finalBossAircraft = new FinalBossAircraft((int)(Main.WINDOW_WIDTH / 2), (int)(Main.WINDOW_HEIGHT * 0.15), 1, 0, 500000);
        finalBossAircraft.setShootStrategy(new FinalBossShootStrategy());
        enemyAircrafts.add(finalBossAircraft);
        finalbossMusicThread.start();
    }



    private void MoveOn()
    {
        setMoveonIndexReady();
        heroAircraft.HPup(100000);
        heroAircraft.setShootStrategy(new LastPowerStrategyHero());
        Bomb_num += 10;
        PowerUpItem_num = 0;
        SpPowerUpItem_num = 0;
        finalbossMusicThread.stopPlayback();
        lastMusic = new MusicThread("src/videos/虻川治 - mile likelihood.wav", true, 1000000, 2, Main.music_en);
        lastMusic.start();
        loop_power_up = true;
        HeroshootDuration = 120;
        setpowerupindexReady();
    }

}
