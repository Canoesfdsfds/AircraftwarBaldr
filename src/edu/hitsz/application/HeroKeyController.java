package edu.hitsz.application;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import edu.hitsz.aircraft.HeroAircraft;

public class HeroKeyController implements KeyListener {
    private Game game;
    private HeroAircraft heroAircraft;
    private Timer timer;
    private int step = 4; // 移动步长
    private int windowWidth; // 游戏窗口宽度
    private int windowHeight; // 游戏窗口高度
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public HeroKeyController(Game game, HeroAircraft heroAircraft, int windowWidth, int windowHeight) {
        this.game = game;
        this.heroAircraft = heroAircraft;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        game.addKeyListener(this);
        game.setFocusable(true);

        timer = new Timer(10, e -> {
            if (movingUp && heroAircraft.getLocationY() - step >= 0) {
                heroAircraft.setLocation(heroAircraft.getLocationX(), heroAircraft.getLocationY() - step);
            }
            if (movingDown && heroAircraft.getLocationY() + step <= windowHeight - heroAircraft.getHeight()) {
                heroAircraft.setLocation(heroAircraft.getLocationX(), heroAircraft.getLocationY() + step);
            }
            if (movingLeft && heroAircraft.getLocationX() - step >= 0) {
                heroAircraft.setLocation(heroAircraft.getLocationX() - step, heroAircraft.getLocationY());
            }
            if (movingRight && heroAircraft.getLocationX() + step <= windowWidth - heroAircraft.getWidth()) {
                heroAircraft.setLocation(heroAircraft.getLocationX() + step, heroAircraft.getLocationY());
            }
        });
        timer.stop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                movingUp = true;
                startMoving();
                break;
            case KeyEvent.VK_DOWN:
                movingDown = true;
                startMoving();
                break;
            case KeyEvent.VK_LEFT:
                movingLeft = true;
                startMoving();
                break;
            case KeyEvent.VK_RIGHT:
                movingRight = true;
                startMoving();
                break;
            case KeyEvent.VK_C:
                if (Game.PowerUpItem_num > 0) {
                    // C键被按下
                    // 触发PowerUpItem的效果
                    PowerupThread powerupThread = new PowerupThread(10000, heroAircraft);
                    powerupThread.start();
                    Game.PowerUpItem_num--;   
                }
                break;
            case KeyEvent.VK_V:
                if (Game.SpPowerUpItem_num > 0) {
                    // V键被按下
                    // 触发SpPowerUpItem的效果
                    SuperupThread superupThread = new SuperupThread(10000, heroAircraft);
                    superupThread.start();
                    Game.SpPowerUpItem_num--;
                }
                break;
            case KeyEvent.VK_B:
                if (Game.Bomb_num > 0) {
                    // B键被按下
                    // 触发BoomItem的效果
                    MusicThread bombmusic = new MusicThread("src/videos/bomb_explosion.wav", false, 3000, 0, Main.music_en);
                    bombmusic.start();
                    Game.setExpindexReady();
                    Game.bombOB.get_bomb();
                    Game.Bomb_num--;
                }
                break;
        }
    }

        @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                movingUp = false;
                stopMoving();
                break;
            case KeyEvent.VK_DOWN:
                movingDown = false;
                stopMoving();
                break;
            case KeyEvent.VK_LEFT:
                movingLeft = false;
                stopMoving();
                break;
            case KeyEvent.VK_RIGHT:
                movingRight = false;
                stopMoving();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void startMoving() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    private void stopMoving() {
        if (!movingUp && !movingDown && !movingLeft && !movingRight && timer.isRunning()) {
            timer.stop();
        }
    }
}