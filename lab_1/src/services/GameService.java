package src.services;

import java.util.Random;

public class GameService {
    private final Random random = new Random();

    public int generateTarget(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public int calculateInitialLives(int level, int rangeLength) {
        if (level == 1)
            return (int) (rangeLength * 0.5); // 50%
        return (int) (rangeLength * 0.25); // 25%
    }

    public int getMultiplier(int level) {
        return (level == 1) ? 5 : 10;
    }
}
