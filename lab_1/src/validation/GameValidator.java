package src.validation;

public class GameValidator {
    public static void validateGuess(int guess, int min, int max) throws Exception {
        if (guess < min || guess > max) {
            throw new Exception("Out of bounds! Please guess between " + min + " and " + max);
        }
    }
}
