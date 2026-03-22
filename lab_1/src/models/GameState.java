package src.models;

public class GameState {
    private int lives;
    private int playerScore = 0;
    private int computerScore = 0; 
    private int targetNumber;
    private boolean isGameOver = false;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = Math.max(0, lives);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void addPlayerScore(int points) {
        this.playerScore += points;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void addComputerScore(int points) {
        this.computerScore += points;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int target) {
        this.targetNumber = target;
    }

    public boolean getIsGameOver() {
        return this.isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}