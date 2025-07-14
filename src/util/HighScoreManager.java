package src.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreManager {
    
    private static final String DATA_DIR = "bin/data/";
    private static final int MAX_SCORES = 3;

    public static boolean updateHighScore(String difficulty, int newScore) {
        String fileName = getFileName(difficulty);
        List<Integer> scores = readScores(fileName);
        
        if (scores.size() < MAX_SCORES || newScore > scores.get(scores.size() - 1)) {
            scores.add(newScore);
            Collections.sort(scores, Collections.reverseOrder());
            
            if (scores.size() > MAX_SCORES) {
                scores = scores.subList(0, MAX_SCORES);
            }
            
            writeScores(fileName, scores);
            return true;
        }
        
        return false;
    }
    
    private static List<Integer> readScores(String fileName) {
        List<Integer> scores = new ArrayList<>();
        File file = new File(DATA_DIR + fileName);
        
        if (!file.exists()) {
            return scores;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int score = Integer.parseInt(line.trim());
                    scores.add(score);
                } catch (NumberFormatException e) {
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading scores from " + fileName + ": " + e.getMessage());
        }
        
        Collections.sort(scores, Collections.reverseOrder());
        return scores;
    }
    
    private static void writeScores(String fileName, List<Integer> scores) {
        File file = new File(DATA_DIR + fileName);
        
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Integer score : scores) {
                writer.write(score.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing scores to " + fileName + ": " + e.getMessage());
        }
    }
    
    private static String getFileName(String difficulty) {
        switch (difficulty.toUpperCase()) {
            case "EASY":
                return "easy.txt";
            case "MEDIUM":
                return "medium.txt";
            case "HARD":
                return "hard.txt";
            default:
                return "easy.txt"; // Default to easy
        }
    }
    
    public static List<Integer> getHighScores(String difficulty) {
        String fileName = getFileName(difficulty);
        return readScores(fileName);
    }
    
    public static boolean isNewHighScore(String difficulty, int score) {
        List<Integer> currentScores = getHighScores(difficulty);
        
        if (currentScores.size() < MAX_SCORES) {
            return true;
        }
        
        return score > currentScores.get(currentScores.size() - 1);
    }
} 