package edu.hitsz.application;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static int mode = 0;//难度
    public static boolean music_en = true;//音乐开关
    public static JFrame frame = new JFrame("Aircraft War");

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        System.out.println(mode);

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game();
        frame.add(game);
        frame.setVisible(true);
        game.action();
    }

    public static void setmode(int mymode)
    {
        mode = mymode;
        return;
    }
    public static void setmusic(boolean mymusic_en)
    {
        music_en = mymusic_en;
        return;
    }
}
