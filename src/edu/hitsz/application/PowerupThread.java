package edu.hitsz.application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.DirectShootStrategyHero;
import edu.hitsz.strategy.LastPowerStrategyHero;
import edu.hitsz.strategy.ScatterShootStrategyHero;

public class PowerupThread extends Thread{

    private long playtime;
    private HeroAircraft heroAircraft;
    PowerupThread(long playtime, HeroAircraft heroAircraft)
    {
        this.playtime = playtime;
        this.heroAircraft = heroAircraft;
    }


    private void play(HeroAircraft heroAircraft)
    {
        Game.setpowerupindexReady();
        MusicThread powerupMusicThread = new MusicThread("src/videos/powerup.wav", false, 1000, 1, Main.music_en);
        powerupMusicThread.start();
        long startTime = System.currentTimeMillis(); // Record start time for playback duration
        heroAircraft.setShootStrategy(new ScatterShootStrategyHero());
        while(System.currentTimeMillis() - startTime < playtime)
        {
            startTime++;
            startTime--;
        }
        if(!(heroAircraft.shootStrategy instanceof LastPowerStrategyHero))
        heroAircraft.setShootStrategy(new DirectShootStrategyHero());
        Game.setPoweroffindexReady();
        MusicThread poweroffMusicThread = new MusicThread("src/videos/poweroff.wav", false, 1200, 0, Main.music_en);
        poweroffMusicThread.start();
    }


    @Override
    public void run() {
        play(heroAircraft);
    }

}
