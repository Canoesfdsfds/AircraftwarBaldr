package edu.hitsz.GUI;

import javax.swing.*;

import edu.hitsz.application.Main;

import java.awt.*;

public class StartPanel extends JPanel {
    private Image image = (Image) new ImageIcon("src/images/title.jpg").getImage();
    protected void paintComponent(Graphics g) {  
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    }
    public StartPanel() {
        // 创建 GridBagLayout 实例
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        // 创建 GridBagConstraints 实例
        GridBagConstraints gbc = new GridBagConstraints();
        // 设置填充方式为水平和垂直都扩展
        gbc.fill = GridBagConstraints.BOTH;
        // 设置组件之间的水平和垂直间距
        gbc.insets = new Insets(10, 50,50, 50);

        // 创建按钮
        JButton easyButton = new JButton("简单模式");
        easyButton.setBackground(new Color(0, 0, 255, 50));  
        
        JButton normalButton = new JButton("普通模式");
        normalButton.setBackground(new Color(0, 255, 0, 50));  
        
        JButton hardButton = new JButton("困难模式");
        hardButton.setBackground(new Color(255, 0, 0, 50));
        // 添加按钮到面板，并设置权重和跨度
        addComponent(this, easyButton, layout, gbc, 0, 0, 1, 1, 1, 0.8);
        addComponent(this, normalButton, layout, gbc, 0, 1, 1, 1, 1, 0.8);
        addComponent(this, hardButton, layout, gbc, 0, 2, 1, 1, 1, 0.8);

        // 创建下拉列表
        JPanel comboBoxPanel = new JPanel();
        JCheckBox soundCheckBox = new JCheckBox("音效", true); // 默认选中
        comboBoxPanel.add(soundCheckBox);
        // 添加下拉列表到面板
        addComponent(this, comboBoxPanel, layout, gbc, 0, 3, 1, 1, 1, 0);

        // 为按钮添加点击事件处理
        easyButton.addActionListener(e -> {
            // 执行主程序
            Main.setmode(0);
            Main.setmusic(soundCheckBox.isSelected());
            Main.main(new String[]{});
            // 隐藏模式面板
            ((JButton)e.getSource()).getTopLevelAncestor().setVisible(false);
        });
        normalButton.addActionListener(e -> {
            // 执行主程序
            Main.setmode(1);
            Main.setmusic(soundCheckBox.isSelected());
            Main.main(new String[]{});
            // 隐藏模式面板
            ((JButton)e.getSource()).getTopLevelAncestor().setVisible(false);
        });
        hardButton.addActionListener(e -> {
            // 执行主程序
            Main.setmode(2);
            Main.setmusic(soundCheckBox.isSelected());
            Main.main(new String[]{});
            // 隐藏模式面板
            ((JButton)e.getSource()).getTopLevelAncestor().setVisible(false);
        });
    }


    // 辅助方法，用于添加组件到面板
    private void addComponent(Container container, Component component,
                              GridBagLayout layout, GridBagConstraints gbc,
                              int gridx, int gridy, int gridwidth, int gridheight,
                              double weightx, double weighty) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        layout.setConstraints(component, gbc);
        container.add(component);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Aircraft War");
            frame.setSize(512, 768);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new StartPanel());
            frame.setVisible(true);
        });
    }
}