package edu.hitsz.application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import edu.hitsz.aircraft.HeroAircraft;


public class MyKeyListener implements KeyListener{
    HeroAircraft heroAircraft;
    public void setHeroAircraft(HeroAircraft heroAircraft)
    {
        this.heroAircraft = heroAircraft;
    }
    @Override
public void keyPressed(KeyEvent e) {
    if (Game.PowerUpItem_num > 0 && e.getKeyCode() == KeyEvent.VK_C) {
        // C键被按下
        // 触发PowerUpItem的效果
        PowerupThread powerupThread = new PowerupThread(10000, heroAircraft);
        powerupThread.start();
        Game.PowerUpItem_num--;   
    } else if (Game.SpPowerUpItem_num > 0 && e.getKeyCode() == KeyEvent.VK_V) {
        // V键被按下
        // 触发SpPowerUpItem的效果
        SuperupThread superupThread = new SuperupThread(10000, heroAircraft);
        superupThread.start();
        Game.SpPowerUpItem_num--;
    } else if (Game.Bomb_num > 0 && e.getKeyCode() == KeyEvent.VK_B) {
        // B键被按下
        // 触发BoomItem的效果
        MusicThread bombmusic = new MusicThread("src/videos/bomb_explosion.wav", false, 3000, 0, Main.music_en);
        bombmusic.start();
        Game.bombOB.get_bomb();
        Game.Bomb_num--;
        System.out.println(Game.Bomb_num);
    }
}
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }


}
