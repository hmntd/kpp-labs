package src.models;

public class Game2048State {
    private int[][] grid = new int[4][4];
    private int currentScore = 0;
    private int bestScore = 0;
    private boolean won = false;

    public int[][] getGrid() {
        return grid;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void addScore(int points) {
        this.currentScore += points;
        if (currentScore > bestScore)
            bestScore = currentScore;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void resetForNewGame() {
        grid = new int[4][4];
        currentScore = 0;
    }

    public boolean getWon() {
        return this.won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public boolean checkFor2048(int[][] grid) {
        if (won) return false;

        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 2048) {
                    won = true;
                    return true;
                }
            }
        }

        return false;
    }
}
