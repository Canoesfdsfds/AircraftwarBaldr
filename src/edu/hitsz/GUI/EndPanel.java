package edu.hitsz.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import records.GameRecord;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EndPanel extends JPanel {

    private static final String RECORDS_FILE0 = "game_records_easy.txt";
    private static final String RECORDS_FILE1 = "game_records_normal.txt";
    private static final String RECORDS_FILE2 = "game_records.txt";
    private static final int MAX_RECORDS = 10;
    private JTable leaderboardTable;

    public EndPanel() {
        createLeaderboardTable();
        simulateEndOfGame(Game.score);
        askAndSaveGameRecord();
        loadRecordsFromFile();
        displayLeaderboard();
    }

    private void loadRecordsFromFile() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Rank");
    model.addColumn("Game Name");
    model.addColumn("Score");
    model.addColumn("Date");
    


    if(Main.mode == 0){
    try (BufferedReader reader = new BufferedReader(new FileReader(RECORDS_FILE0))) {
        String line;
        int rank = 1;
        List<GameRecord> records = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String gameName = parts[0].trim();
                int gameScore = Integer.parseInt(parts[1].trim());
                String gameDate = parts[2].trim();
                records.add(new GameRecord(gameName, gameScore, gameDate));
            }
        }
        // Sort the records by score descending
        records.sort(Comparator.comparingInt(GameRecord::getGameScore).reversed());
        // Add sorted records to table model
        for (GameRecord record : records) {
            model.addRow(new Object[]{rank, record.getGameName(), record.getGameScore(), record.getGameDate()});
            rank++;
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error loading records from file: " + e.getMessage());
    }
}
    if(Main.mode == 1){
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORDS_FILE1))) {
            String line;
            int rank = 1;
            List<GameRecord> records = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String gameName = parts[0].trim();
                    int gameScore = Integer.parseInt(parts[1].trim());
                    String gameDate = parts[2].trim();
                    records.add(new GameRecord(gameName, gameScore, gameDate));
                }
            }
            // Sort the records by score descending
            records.sort(Comparator.comparingInt(GameRecord::getGameScore).reversed());
            // Add sorted records to table model
            for (GameRecord record : records) {
                model.addRow(new Object[]{rank, record.getGameName(), record.getGameScore(), record.getGameDate()});
                rank++;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading records from file: " + e.getMessage());
        }
    }
    if(Main.mode == 2){
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORDS_FILE2))) {
            String line;
            int rank = 1;
            List<GameRecord> records = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String gameName = parts[0].trim();
                    int gameScore = Integer.parseInt(parts[1].trim());
                    String gameDate = parts[2].trim();
                    records.add(new GameRecord(gameName, gameScore, gameDate));
                }
            }
            // Sort the records by score descending
            records.sort(Comparator.comparingInt(GameRecord::getGameScore).reversed());
            // Add sorted records to table model
            for (GameRecord record : records) {
                model.addRow(new Object[]{rank, record.getGameName(), record.getGameScore(), record.getGameDate()});
                rank++;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading records from file: " + e.getMessage());
        }
    }




    leaderboardTable.setModel(model);
    }

    private void createLeaderboardTable() {
        leaderboardTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        add(scrollPane);
    }

    private void displayLeaderboard() {
        // Assuming you want to display the leaderboard directly after loading records
        JFrame leaderboardFrame = new JFrame("Leaderboard");
        leaderboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        leaderboardFrame.setSize(512, 768);
        leaderboardFrame.setLocationRelativeTo(null);

        if(Main.mode == 0){
            JLabel titleLabel = new JLabel("简单模式排行榜");
            titleLabel.setFont(new Font("Yahei", Font.BOLD, 24)); // 设置标题字体
            leaderboardFrame.add(titleLabel, BorderLayout.NORTH); // 将标题添加到顶部
        }
        if(Main.mode == 1){
            JLabel titleLabel = new JLabel("普通模式排行榜");
            titleLabel.setFont(new Font("Yahei", Font.BOLD, 24)); // 设置标题字体
            leaderboardFrame.add(titleLabel, BorderLayout.NORTH); // 将标题添加到顶部
        }
        if(Main.mode == 2){
            JLabel titleLabel = new JLabel("困难模式排行榜");
            titleLabel.setFont(new Font("Yahei", Font.BOLD, 24)); // 设置标题字体
            leaderboardFrame.add(titleLabel, BorderLayout.NORTH); // 将标题添加到顶部
        }
    
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        leaderboardFrame.getContentPane().add(scrollPane);


    
        // Create delete button
        JButton deleteButton = new JButton("删除记录");
        deleteButton.addActionListener(e -> {deleteSelectedRecord();((JButton)e.getSource()).getTopLevelAncestor().setVisible(false);}); // Attach action listener to deleteButton
        leaderboardFrame.getContentPane().add(deleteButton, BorderLayout.SOUTH); // Add deleteButton to the bottom
    
        leaderboardFrame.setVisible(true);
    }

    private static void simulateEndOfGame(int score) {
        // Simulate end of the game
        if(Game.IfVictory())
        JOptionPane.showMessageDialog(null, "You Win!");
        else
        JOptionPane.showMessageDialog(null, "Game Over!");
        
        // Prompt user to enter game name using input dialog
        String gameName = JOptionPane.showInputDialog("Enter your game name:").trim();
        
        // Get current system time
        String gameDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        
        // Create a game record object
        GameRecord gameRecord = new GameRecord(gameName, score, gameDate);
        
        // Add the record to DAO
        if(Main.mode == 0)
        Game.recordDaoeasy.doAdd(gameRecord);
        if(Main.mode == 1)
        Game.recordDaonormal.doAdd(gameRecord);
        if(Main.mode == 2)
        Game.recordDao.doAdd(gameRecord);
    }


    public void askAndSaveGameRecord() {
        // Ask user if they want to save the record
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to save your game record?", "Save Record", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            saveGameRecord();
        }
    }

    private static void saveGameRecord() {
        // Get the latest records
        if(Main.mode == 0){
        List<GameRecord> allRecords = Game.recordDaoeasy.Get_All_Records();
        // Sort the records by score descending
        allRecords.sort(Comparator.comparingInt(GameRecord::getGameScore).reversed());
        // Truncate the list to maximum records
        if (allRecords.size() > MAX_RECORDS) {
            allRecords = allRecords.subList(0, MAX_RECORDS);
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_FILE0, true))) {
            for (GameRecord record : allRecords) {
                writer.write(record.getGameName().replaceAll("\\s", "") + "," + record.getGameScore() + "," + record.getGameDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving records to file: " + e.getMessage());
        }
    }
    if(Main.mode == 1){
        List<GameRecord> allRecords = Game.recordDaonormal.Get_All_Records();
        // Sort the records by score descending
        allRecords.sort(Comparator.comparingInt(GameRecord::getGameScore).reversed());
        // Truncate the list to maximum records
        if (allRecords.size() > MAX_RECORDS) {
            allRecords = allRecords.subList(0, MAX_RECORDS);
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_FILE1, true))) {
            for (GameRecord record : allRecords) {
                writer.write(record.getGameName().replaceAll("\\s", "") + "," + record.getGameScore() + "," + record.getGameDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving records to file: " + e.getMessage());
        }
    }
    if(Main.mode == 2){
        List<GameRecord> allRecords = Game.recordDao.Get_All_Records();
        // Sort the records by score descending
        allRecords.sort(Comparator.comparingInt(GameRecord::getGameScore).reversed());
        // Truncate the list to maximum records
        if (allRecords.size() > MAX_RECORDS) {
            allRecords = allRecords.subList(0, MAX_RECORDS);
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_FILE2, true))) {
            for (GameRecord record : allRecords) {
                writer.write(record.getGameName().replaceAll("\\s", "") + "," + record.getGameScore() + "," + record.getGameDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving records to file: " + e.getMessage());
        }
    }
    }

    private void deleteSelectedRecord() {
        int selectedRow = leaderboardTable.getSelectedRow();
        if (selectedRow != -1) { // Check if any row is selected
            DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();
            model.removeRow(selectedRow); // Remove the selected row from the table
    
            // Update the records file after removing the selected record
            saveRecordsToFile(model);
    
            // Display a message indicating successful deletion
            JOptionPane.showMessageDialog(this, "Record deleted successfully!");
    
            // Close the current leaderboard frame

    
            // Load records again and display the updated leaderboard
            loadRecordsFromFile();
            displayLeaderboard();
        } else {
            // If no row is selected, display a message
            JOptionPane.showMessageDialog(this, "Please select a record to delete.");
        }
    }

    private void saveRecordsToFile(DefaultTableModel model) {
        if(Main.mode == 0){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_FILE0))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.write(model.getValueAt(i, 1) + "," +
                             model.getValueAt(i, 2) + "," +
                             model.getValueAt(i, 3));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving records to file: " + e.getMessage());
        }
    }
    if(Main.mode == 1){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_FILE1))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.write(model.getValueAt(i, 1) + "," +
                             model.getValueAt(i, 2) + "," +
                             model.getValueAt(i, 3));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving records to file: " + e.getMessage());
        }
    }
    if(Main.mode == 2){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_FILE2))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.write(model.getValueAt(i, 1) + "," +
                             model.getValueAt(i, 2) + "," +
                             model.getValueAt(i, 3));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving records to file: " + e.getMessage());
        }
    }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //JFrame frame = new JFrame("Leaderboard");
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setSize(500, 400);
            //frame.setLocationRelativeTo(null);
            //frame.getContentPane().add(endPanel);
            //frame.setVisible(true);
            EndPanel endPanel = new EndPanel();
        });
    }
}
